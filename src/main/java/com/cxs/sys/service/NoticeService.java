package com.cxs.sys.service;

import com.cxs.sys.domain.Notice;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.NoticeVo;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 20:57
 */
public interface NoticeService {

    DataGridView queryAllNotice(NoticeVo noticeVo);

    Integer addNotice(NoticeVo noticeVo);

    Integer updateNotice(NoticeVo noticeVo);

    Integer deleteNotice(Integer id);


    Notice queryNoticeById(Integer id);
}
