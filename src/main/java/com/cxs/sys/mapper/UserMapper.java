package com.cxs.sys.mapper;

import com.cxs.sys.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryUserByLoginname(String loginname);

    //分页查询的用户
    List<User> queryAllUser(User user);

    Integer queryMaxOrderNum();

    //保存用户和角色的关系
    void saveRoleUser(Integer rid, Integer uid);
}