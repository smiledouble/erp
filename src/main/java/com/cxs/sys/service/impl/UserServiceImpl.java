package com.cxs.sys.service.impl;

import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Role;
import com.cxs.sys.domain.User;
import com.cxs.sys.mapper.RoleMapper;
import com.cxs.sys.mapper.UserMapper;
import com.cxs.sys.service.UserService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.Md5Utils;
import com.cxs.sys.utils.WebUtils;
import com.cxs.sys.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User getUserByLoginname(String loginname) {
        return this.userMapper.queryUserByLoginname(loginname);
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = null;
        List<User> users = null;
        page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());

        users = this.userMapper.queryAllUser(userVo);
        if (page.size() == 0) {
            if (userVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), users);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(userVo.getPage() - 1, userVo.getLimit());
                users = this.userMapper.queryAllUser(userVo);
            }
        }
        return new DataGridView(page.getTotal(), users);
    }

    /**
     * 根据部门id查询用户
     *
     * @param userVo
     * @return
     */
    @Override
    public List<User> queryUserByDeptId(UserVo userVo) {
        return this.userMapper.queryAllUser(userVo);
    }

    @Override
    public Integer getMaxOrderNumber() {
        return this.userMapper.queryMaxOrderNum();
    }

    @Override
    public Integer addUser(UserVo userVo) {
        return this.userMapper.insertSelective(userVo);
    }

    @Override
    public Integer updateUser(UserVo userVo) {
        return this.userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public Integer deleteUser(Integer id) {
        //删除用户的同事要删除 用户和角色的表的数据
        this.roleMapper.deleteRoleByUserId(id);
        return this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户的id 查询用户对象
     *
     * @param userVo
     * @return
     */
    @Override
    public User queryUserByUserId(UserVo userVo) {
        return this.userMapper.selectByPrimaryKey(userVo.getId());
    }

    /**
     * 重置密码
     *
     * @param userVo
     * @return
     */
    @Override
    public Integer resetPwd(UserVo userVo) {
        //设置盐
        String salt = Md5Utils.createRandomStr();
        userVo.setSalt(salt);
        userVo.setPwd(Md5Utils.md5(SYSConstant.USER_PWD_DEFAULT, salt, SYSConstant.PWD_SANLIE));
        Integer i = this.userMapper.updateByPrimaryKeySelective(userVo);
        return i;
    }

    /**
     * 查询当前用户所拥有的角色
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadUserRole(UserVo userVo) {
        //首先查询所有的角色
        Role role = new Role();
        role.setAvailable(SYSConstant.SYS_AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(role);
        //在查询当前用户所拥有的角色 根据 id查询
        List<Role> currentRole = this.roleMapper.queryRoleByUserId(userVo.getId());

        List<Map<String, Object>> list = new ArrayList<>();

        for (Role r1 : allRole) {
            Boolean checked = false;
            for (Role r2 : currentRole) {
                if (r1.getId().intValue() == r2.getId().intValue()) {
                    checked = true;
                    break;
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", r1.getId());
            map.put("name", r1.getName());
            map.put("remark", r1.getRemark());
            if (checked) {
                map.put("LAY_CHECKED", checked);
            }
            list.add(map);
        }

        return new DataGridView(list.size() + 0L, list);
    }

    /**
     * 保存用户和角色的关系
     *
     * @param userVo
     * @return
     */
    @Override
    public Integer saveRoleUser(UserVo userVo) {
        Integer uid = userVo.getId();
        Integer[] rids = userVo.getRids();
        this.roleMapper.deleteRoleByUserId(uid);
        if (rids != null && rids.length > 0) {
            for (Integer rid : rids) {
                this.userMapper.saveRoleUser(rid, uid);
            }
        }
        return 1;
    }

    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @Override
    public Integer changeUserPwd(UserVo userVo) {

        User user = WebUtils.getCurrentUser();
        userVo.setId(user.getId());
        String salt = Md5Utils.createRandomStr();
        String newPwd = Md5Utils.md5(userVo.getPwd(), salt, SYSConstant.PWD_SANLIE);
        userVo.setSalt(salt);
        userVo.setPwd(newPwd);
        Integer i = this.userMapper.updateByPrimaryKeySelective(userVo);
        return i;
    }
}