package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxClient;

public interface WxClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxClient record);

    int insertSelective(WxClient record);

    WxClient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxClient record);

    int updateByPrimaryKey(WxClient record);
}