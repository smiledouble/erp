package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.service.LogService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 17:58
 */
@RestController
public class LogInfoController {
    private static final String MODEL = "logInfo";


    @Autowired
    private LogService logService;

    @GetMapping(MODEL + "/loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogVo logVo) {
        if (null == logVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.logService.queryAllLog(logVo);
    }


    @PostMapping(MODEL + "/deleteLogInfo")
    public BaseResult<?> deleteLogInfo(LogVo logVo) {
        String msg = "";
        if (null == logVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.logService.deleteLog(logVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.error(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }
}
