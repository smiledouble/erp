package com.cxs.sys.mapper;

import com.cxs.sys.domain.Dept;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> queryAllDept(Dept dept);

    Integer queryMaxNumber();

    Integer queryDeptHasChild(Integer id);
}