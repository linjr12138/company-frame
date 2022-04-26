package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxClientTweet;

public interface WxClientTweetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxClientTweet record);

    int insertSelective(WxClientTweet record);

    WxClientTweet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxClientTweet record);

    int updateByPrimaryKey(WxClientTweet record);
}