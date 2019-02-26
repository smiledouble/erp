package com.cxs.sys.service.impl;

import com.cxs.sys.domain.Log;
import com.cxs.sys.mapper.LogMapper;
import com.cxs.sys.service.LogService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.LogVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 17:45
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public DataGridView queryAllLog(LogVo logVo) {
        Page<Object> page = null;
        List<Log> logs = null;
        page = PageHelper.startPage(logVo.getPage(), logVo.getLimit());

        logs = this.logMapper.queryLog(logVo);
        if (page.size() == 0) {
            if (logVo.getPage() == 1) {
              //  page = PageHelper.startPage(logVo.getPage(), logVo.getLimit());
               // logs = this.logMapper.queryLog(logVo);
                return new DataGridView(page.getTotal(), logs);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(logVo.getPage() - 1, logVo.getLimit());
                logs = this.logMapper.queryLog(logVo);
            }
        }
        return new DataGridView(page.getTotal(), logs);
    }

    @Override
    public void addLog(LogVo logVo) {
        this.logMapper.insertSelective(logVo);
    }

    @Override
    public Integer deleteLog(Integer id) {
        return this.logMapper.deleteByPrimaryKey(id);
    }
}
