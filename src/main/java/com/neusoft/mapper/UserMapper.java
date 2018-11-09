package com.neusoft.mapper;

import com.neusoft.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectBySlugName(User user);
    List<User> selectAll();
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}