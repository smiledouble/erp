package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Dept;
import com.cxs.sys.service.DeptService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import com.cxs.sys.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/22 10:13
 */
@RestController
public class DeptController {

    private static final String MODEL = "dept";

    @Autowired
    private DeptService deptService;


    /**
     * 加载左边的树
     * @param deptVo
     * @return
     */
    @PostMapping(MODEL + "/loadDeptLeftTreeJson")
    public List<TreeNode> loadDeptLeftTreeJson(DeptVo deptVo) {
        if (deptVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        List<TreeNode> nodes = new ArrayList<>();
        List<Dept> depts = this.deptService.queryDeptForList(deptVo);
        for (Dept dept : depts) {
            Boolean isParent = dept.getParent() == SYSConstant.DEPT_PARENT_TRUE ? true : false;
            Boolean open = dept.getOpen() == SYSConstant.DEPT_OPEN_TRUE ? true : false;
            nodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getName(), isParent, open));
        }
        return nodes;
    }

    /**
     * 加载右边的数据表格
     * @param deptVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo) {
        return this.deptService.queryAllDept(deptVo);
    }

    /**
     * 添加部门
     *
     * @param deptVo
     * @return
     */

    @PostMapping(MODEL + "/addDept")
    public BaseResult<?> addDept( DeptVo deptVo) {
        String msg = "";
        if (deptVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            //添加部门 以后还需要找到他的父部门 把它变成父节点，在service里面写 是同一个事物
            Integer i = this.deptService.addDept(deptVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 获取最大排序码
     * @return
     */
    @GetMapping(MODEL+"/getMaxOrderNumber")
    public BaseResult<?> getMaxOrderNumber(){
        Integer maxOrderNumber  = this.deptService.queryMaxOrderNumber();
        return BaseResult.success(maxOrderNumber+1);
    }

    /**
     * 修改部门
     *
     * @param deptVo
     * @return
     */

    @PostMapping(MODEL + "/updateDept")
    public BaseResult<?> updateDept( DeptVo deptVo) {
        String msg = "";
        if (deptVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.deptService.updateDept(deptVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }


    /**
     * 判断该部门是否有子部门
     * @param deptVo
     * @return
     */

    @PostMapping(MODEL+"/thisDeptHasChild")
    public BaseResult<?> thisDeptHasChild(DeptVo deptVo){
        if(deptVo==null){
            throw new BaseException(ResultCode.FAIL);
        }
        Map<String ,Object> map = new HashMap<>();
        Integer child=  this.deptService.queryDeptHasChild(deptVo.getId());
        map.put("flag",child>0?true:false);
        map.put("message",SYSConstant.DEPT_CHILD_TRUE);
        return BaseResult.success(map);
    }


    /**
     * 删除部门
     *
     * @param deptVo
     * @return
     */
    @PostMapping(MODEL + "/deleteDept")
    public BaseResult<?> deleteDept(DeptVo deptVo) {
        String msg = "";
        if (deptVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.deptService.deleteDept(deptVo);
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }


}
