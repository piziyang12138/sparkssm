package com.neusoft.mapper;

import com.neusoft.bean.CommentNotes;

public interface CommentNotesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentNotes record);

    int insertSelective(CommentNotes record);

    CommentNotes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentNotes record);

    int updateByPrimaryKey(CommentNotes record);
}