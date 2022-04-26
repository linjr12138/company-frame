package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxCommentReply;

public interface WxCommentReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxCommentReply record);

    int insertSelective(WxCommentReply record);

    WxCommentReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxCommentReply record);

    int updateByPrimaryKey(WxCommentReply record);
}