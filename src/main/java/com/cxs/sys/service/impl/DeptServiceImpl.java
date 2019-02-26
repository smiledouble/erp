package com.cxs.sys.service.impl;

import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Dept;
import com.cxs.sys.domain.Notice;
import com.cxs.sys.mapper.DeptMapper;
import com.cxs.sys.service.DeptService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.DeptVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/22 10:03
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        Page<Object> page = null;
        List<Dept> depts = null;
        page = PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
        depts = this.deptMapper.queryAllDept(deptVo);
        if (page.size() == 0) {
            if (deptVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), depts);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(deptVo.getPage() - 1, deptVo.getLimit());
                depts = this.deptMapper.queryAllDept(deptVo);
            }
        }
        return new DataGridView(page.getTotal(), depts);
    }

    @Override
    public List<Dept> queryDeptForList(DeptVo deptVo) {
        return this.deptMapper.queryAllDept(deptVo);
    }

    /**
     * 添加部门的方法
     *
     * @param deptVo
     * @return
     */
    @Override
    public Integer addDept(DeptVo deptVo) {
        int i = this.deptMapper.insertSelective(deptVo);
        //设置他的父部门为父节点
        Dept dept = new Dept();
        dept.setParent(SYSConstant.DEPT_PARENT_TRUE);
        dept.setId(deptVo.getPid());
        this.deptMapper.updateByPrimaryKeySelective(dept);
        return i;
    }

    /**
     * 修改部门的方法
     *
     * @param deptVo
     * @return
     */
    @Override
    public Integer updateDept(DeptVo deptVo) {
        return this.deptMapper.updateByPrimaryKeySelective(deptVo);
    }

    @Override
    public Integer deleteDept(DeptVo deptVo) {
        Integer i = this.deptMapper.deleteByPrimaryKey(deptVo.getId());
        //调用下面的根据节点id 查询有没有子节点
        Integer count = this.deptMapper.queryDeptHasChild(deptVo.getPid());
        if (count < 1 && deptVo.getPid() != 1) {
            //说明没有子节点了 修改把他parent改成0
            Dept dept = new Dept();
            dept.setId(deptVo.getPid());
            dept.setParent(SYSConstant.DEPT_PARENT_FALSE);
            this.deptMapper.updateByPrimaryKeySelective(dept);
        }
        return i;
    }

    @Override
    public Integer queryMaxOrderNumber() {
        return this.deptMapper.queryMaxNumber();
    }

    @Override
    public Integer queryDeptHasChild(Integer id) {
        return this.deptMapper.queryDeptHasChild(id);
    }
}
