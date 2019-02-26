package com.cxs.bus.controller;

import com.cxs.bus.service.CustomerService;
import com.cxs.bus.vo.CustomerVo;
import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:04
 */
@RestController
public class CustomerController {

    private static final String MODEL = "customer";

    @Autowired
    private CustomerService customerService;

    /**
     * 加载客户
     *
     * @param customerVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        if (null == customerVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.customerService.loadAllCustomer(customerVo);
    }

    /**
     * 添加客户
     *
     * @param customerVo
     * @return
     */

    @PostMapping(MODEL + "/addCustomer")
    public BaseResult<?> addCustomer( CustomerVo customerVo) {
        String msg = "";
        if (customerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.customerService.addCustomer(customerVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 修改客户
     *
     * @param customerVo
     * @return
     */

    @PostMapping(MODEL + "/updateCustomer")
    public BaseResult<?> updateCustomer( CustomerVo customerVo) {
        String msg = "";
        if (customerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.customerService.updateCustomer(customerVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 删除客户
     *
     * @param customerVo
     * @return
     */
    @PostMapping(MODEL + "/deleteCustomer")
    public BaseResult<?> deleteCustomer(CustomerVo customerVo) {
        String msg = "";
        if (customerVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.customerService.deleteCustomer(customerVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

}
