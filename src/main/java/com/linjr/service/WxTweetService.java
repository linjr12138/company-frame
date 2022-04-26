package com.linjr.service;

import com.linjr.entity.db1.WxTweet;

import java.util.List;

public interface WxTweetService {


    /*
    *
    * 查找全部推文
    *
    * */
    List<WxTweet> selectAllTweet();

    /*
    *
    * 发布推文
    *
    *  */
    void addWxTweet(WxTweet wxTweet);

    /**
     * 查找推文
     * @param id
     * @return
     */
    WxTweet selectOneTweet(Integer id);
}
