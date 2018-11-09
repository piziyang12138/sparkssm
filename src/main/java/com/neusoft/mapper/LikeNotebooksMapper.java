package com.neusoft.mapper;

import com.neusoft.bean.LikeNotebooks;

public interface LikeNotebooksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeNotebooks record);

    int insertSelective(LikeNotebooks record);

    LikeNotebooks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeNotebooks record);

    int updateByPrimaryKey(LikeNotebooks record);
}