package com.neusoft.mapper;

import com.neusoft.bean.LikeColls;

public interface LikeCollsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeColls record);

    int insertSelective(LikeColls record);

    LikeColls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeColls record);

    int updateByPrimaryKey(LikeColls record);
}