package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxTweetComment;

public interface WxTweetCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxTweetComment record);

    int insertSelective(WxTweetComment record);

    WxTweetComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxTweetComment record);

    int updateByPrimaryKey(WxTweetComment record);
}