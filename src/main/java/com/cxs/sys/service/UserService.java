package com.cxs.sys.service;

import com.cxs.sys.domain.User;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.UserVo;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:02
 */
public interface UserService {

    User getUserByLoginname(String loginname);

    //分页查询
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 根据部门id查询用户
     * @param userVo
     * @return
     */
    List<User> queryUserByDeptId(UserVo userVo);

    Integer getMaxOrderNumber();

    Integer addUser(UserVo userVo);


    Integer updateUser(UserVo userVo);

    Integer deleteUser(Integer id);

    /**
     * 根据用户的id 查询用户对象
     * @param userVo
     * @return
     */
    User queryUserByUserId(UserVo userVo);

    /**
     * 重置密码
     * @param userVo
     * @return
     */
    Integer resetPwd(UserVo userVo);

    /**
     * 查询当前用户所拥有的角色
     * @param userVo
     * @return
     */
    DataGridView loadUserRole(UserVo userVo);

    /**
     * 保存用户和角色的关系
     * @param userVo
     * @return
     */
    Integer saveRoleUser(UserVo userVo);

    //修改密码
    Integer changeUserPwd(UserVo userVo);
}
