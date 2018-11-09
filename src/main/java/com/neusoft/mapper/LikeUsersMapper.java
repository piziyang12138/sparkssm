package com.neusoft.mapper;

import com.neusoft.bean.LikeUsers;

public interface LikeUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeUsers record);

    int insertSelective(LikeUsers record);

    LikeUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeUsers record);

    int updateByPrimaryKey(LikeUsers record);
}