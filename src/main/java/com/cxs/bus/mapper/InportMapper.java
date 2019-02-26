package com.cxs.bus.mapper;

import com.cxs.bus.domain.Inport;

import java.util.List;

public interface InportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inport record);

    int insertSelective(Inport record);

    Inport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inport record);

    int updateByPrimaryKey(Inport record);

    //查询所有的进货信息
    List<Inport> queryAllInport(Inport inport);
}