package com.neusoft.mapper;

import com.neusoft.bean.ShareNote;

public interface ShareNoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareNote record);

    int insertSelective(ShareNote record);

    ShareNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareNote record);

    int updateByPrimaryKey(ShareNote record);
}