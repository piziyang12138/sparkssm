package com.neusoft.mapper;

import com.neusoft.bean.RewardNotes;

public interface RewardNotesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardNotes record);

    int insertSelective(RewardNotes record);

    RewardNotes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RewardNotes record);

    int updateByPrimaryKey(RewardNotes record);
}