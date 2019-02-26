package com.cxs.sys.mapper;

import com.cxs.sys.domain.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    //模糊查询的方法
    List<Log> queryLog(Log log);
}