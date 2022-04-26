package com.linjr.service;

import com.linjr.entity.db1.WxCommentReply;
import com.linjr.entity.db1.WxTweetComment;

public interface WxTweetCommentService {

    /**
     * 客户添加推文评论
     * @param wxTweetComment
     */
    void addComment(WxTweetComment wxTweetComment);

    /**
     * 评论回复
     * @param wxCommentReply
     */
    void addCommentReply(WxCommentReply wxCommentReply);
}
