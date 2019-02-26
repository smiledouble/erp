package com.cxs.bus.controller;

import com.cxs.bus.constant.BUSConstant;
import com.cxs.bus.domain.Provider;
import com.cxs.bus.service.ProviderService;
import com.cxs.bus.vo.ProviderVo;
import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:04
 */
@RestController
public class ProviderController {

    private static final String MODEL = "provider";

    @Autowired
    private ProviderService providerService;

    /**
     * 加载供应商
     *
     * @param providerVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo) {
        if (null == providerVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.providerService.loadAllProvider(providerVo);
    }


    //加载供应商的树loadProviderLeftTreeJson
    @PostMapping(MODEL + "/loadProviderLeftTreeJson")
    public List<TreeNode> loadProviderLeftTreeJson(ProviderVo providerVo) {
        if (providerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        List<TreeNode> nodes = new ArrayList<>();
        List<Provider> providers = this.providerService.queryAllProvider(providerVo);
        //自己创建所有供应商
        Provider provider = new Provider();
        provider.setProvidername(BUSConstant.PROVIDER_PARENT_NAME);
        provider.setId(0);
        nodes.add(new TreeNode(provider.getId(), 0, provider.getProvidername(), true, true));
        for (Provider p : providers) {
            Boolean isParent = false;
            Boolean open = false;
            nodes.add(new TreeNode(p.getId(), 0, p.getProvidername(), isParent, open));
        }

        return nodes;
    }


    /**
     * 添加供应商
     *
     * @param providerVo
     * @return
     */

    @PostMapping(MODEL + "/addProvider")
    public BaseResult<?> addProvider(ProviderVo providerVo) {
        String msg = "";
        if (providerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.providerService.addProvider(providerVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 修改供应商
     *
     * @param providerVo
     * @return
     */

    @PostMapping(MODEL + "/updateProvider")
    public BaseResult<?> updateProvider(ProviderVo providerVo) {
        String msg = "";
        if (providerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.providerService.updateProvider(providerVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 删除供应商
     *
     * @param providerVo
     * @return
     */
    @PostMapping(MODEL + "/deleteProvider")
    public BaseResult<?> deleteProvider(ProviderVo providerVo) {
        String msg = "";
        if (providerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.providerService.deleteProvider(providerVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }
}
