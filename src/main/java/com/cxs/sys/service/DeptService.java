package com.cxs.sys.service;

import com.cxs.sys.domain.Dept;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.DeptVo;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 17:41
 */
public interface DeptService {

    //分页查询
    DataGridView queryAllDept(DeptVo deptVo);

    //全查询
    List<Dept> queryDeptForList(DeptVo deptVo);

    //添加
    Integer addDept(DeptVo deptVo);

    //修改
    Integer updateDept(DeptVo deptVo);

    //删除
    Integer deleteDept(DeptVo deptVo);

    //查询最大的排序码
    Integer queryMaxOrderNumber();

    //查询该部门有没有子部门
    Integer queryDeptHasChild(Integer id);
}
