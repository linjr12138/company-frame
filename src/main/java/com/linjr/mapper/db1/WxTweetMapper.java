package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxTweet;

import java.util.List;

public interface WxTweetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxTweet record);

    int insertSelective(WxTweet record);

    WxTweet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxTweet record);

    int updateByPrimaryKey(WxTweet record);

    List<WxTweet> selectAll();
}