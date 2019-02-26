package com.cxs.sys.service;

import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.LogVo;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 17:41
 */
public interface LogService {

    //全查询
    DataGridView queryAllLog(LogVo logVo);
    //添加日志
    void addLog(LogVo logVo);
    //删除日志
    Integer deleteLog(Integer id);
}
