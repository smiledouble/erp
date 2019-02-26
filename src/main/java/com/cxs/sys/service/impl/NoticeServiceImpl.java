package com.cxs.sys.service.impl;

import com.cxs.bus.domain.Customer;
import com.cxs.sys.domain.Notice;
import com.cxs.sys.mapper.NoticeMapper;
import com.cxs.sys.service.NoticeService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.NoticeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:02
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public DataGridView queryAllNotice(NoticeVo noticeVo) {
        Page<Object> page = null;
        List<Notice> notices = null;
        page = PageHelper.startPage(noticeVo.getPage(), noticeVo.getLimit());

        notices = this.noticeMapper.queryAllNotice(noticeVo);
        if (page.size() == 0) {
            if (noticeVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), notices);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(noticeVo.getPage() - 1, noticeVo.getLimit());
                notices = this.noticeMapper.queryAllNotice(noticeVo);
            }
        }
        return new DataGridView(page.getTotal(), notices);
    }

    @Override
    public Integer addNotice(NoticeVo noticeVo) {
        return this.noticeMapper.insertSelective(noticeVo);
    }

    @Override
    public Integer updateNotice(NoticeVo noticeVo) {
        return this.noticeMapper.updateByPrimaryKeySelective(noticeVo);
    }

    @Override
    public Integer deleteNotice(Integer id) {
        return this.noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Notice queryNoticeById(Integer id) {
        return this.noticeMapper.selectByPrimaryKey(id);
    }
}
