package com.cxs.bus.controller;

import com.cxs.bus.service.InportService;
import com.cxs.bus.vo.InportVo;
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
public class InportController {

    private static final String MODEL = "inport";

    @Autowired
    private InportService inportService;

    /**
     * 加载进货
     *
     * @param inportVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo) {
        if (null == inportVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.inportService.loadAllInport(inportVo);
    }

    /**
     * 添加进货
     *
     * @param inportVo
     * @return
     */

    @PostMapping(MODEL + "/addInport")
    public BaseResult<?> addInport(InportVo inportVo) {
        String msg = "";
        if (inportVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.inportService.addInport(inportVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 删除进货
     *
     * @param inportVo
     * @return
     */
    @PostMapping(MODEL + "/deleteInport")
    public BaseResult<?> deleteInport(InportVo inportVo) {
        String msg = "";
        if (inportVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.inportService.deleteInport(inportVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

}
