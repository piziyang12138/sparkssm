package com.neusoft.mapper;

import com.neusoft.bean.LikeComment;

public interface LikeCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeComment record);

    int insertSelective(LikeComment record);

    LikeComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeComment record);

    int updateByPrimaryKey(LikeComment record);
}