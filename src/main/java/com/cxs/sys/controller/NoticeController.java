package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Notice;
import com.cxs.sys.service.NoticeService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.WebUtils;
import com.cxs.sys.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:04
 */
@RestController
public class NoticeController {

    private static final String MODEL = "notice";

    @Autowired
    private NoticeService noticeService;

    /**
     * 加载系统公告
     *
     * @param noticeVo
     * @return
     */
    @RequestMapping(MODEL + "/loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo) {
        if (null == noticeVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.noticeService.queryAllNotice(noticeVo);
    }

    /**
     * 发布新闻
     *
     * @param noticeVo
     * @return
     */

    @PostMapping(MODEL + "/addNotice")
    public BaseResult<?> addNotice( NoticeVo noticeVo) {
        String msg = "";
        if (noticeVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            noticeVo.setOpername(WebUtils.getCurrentUser().getName());
            noticeVo.setCreatetime(new Date());
            Integer i = this.noticeService.addNotice(noticeVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 修改新闻
     *
     * @param noticeVo
     * @return
     */

    @PostMapping(MODEL + "/updateNotice")
    public BaseResult<?> updateNotice( NoticeVo noticeVo) {
        String msg = "";
        if (noticeVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            noticeVo.setOpername(WebUtils.getCurrentUser().getName());
            Integer i = this.noticeService.updateNotice(noticeVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 删除新闻
     *
     * @param noticeVo
     * @return
     */
    @PostMapping(MODEL + "/deleteNotice")
    public BaseResult<?> deleteNotice(NoticeVo noticeVo) {
        String msg = "";
        if (noticeVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.noticeService.deleteNotice(noticeVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 查看公告
     */
    @RequestMapping(MODEL+"/loadNoticeById")
    public Notice showNotice(NoticeVo noticeVo) {
        Notice notice = this.noticeService.queryNoticeById(noticeVo.getId());
        return notice;
    }

}
