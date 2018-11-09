package com.neusoft.mapper;

import com.neusoft.bean.LikeNotes;

public interface LikeNotesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeNotes record);

    int insertSelective(LikeNotes record);

    LikeNotes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeNotes record);

    int updateByPrimaryKey(LikeNotes record);
}