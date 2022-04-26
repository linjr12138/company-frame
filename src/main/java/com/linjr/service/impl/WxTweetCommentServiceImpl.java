package com.linjr.service.impl;

import com.linjr.entity.db1.WxCommentReply;
import com.linjr.entity.db1.WxTweetComment;
import com.linjr.exception.BusinessException;
import com.linjr.exception.code.BaseResponseCode;
import com.linjr.mapper.db1.WxCommentReplyMapper;
import com.linjr.mapper.db1.WxTweetCommentMapper;
import com.linjr.service.WxTweetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxTweetCommentServiceImpl implements WxTweetCommentService {

    @Autowired
    private WxTweetCommentMapper wxTweetCommentMapper;

    @Autowired
    private WxCommentReplyMapper wxCommentReplyMapper;

    @Override
    public void addComment(WxTweetComment wxTweetComment) {
        int i  = wxTweetCommentMapper.insertSelective(wxTweetComment);
        if (i < 1){
            throw new BusinessException(BaseResponseCode.Wx_COMMENT_ERROR);
        }
    }

    @Override
    public void addCommentReply(WxCommentReply wxCommentReply) {
        int i = wxCommentReplyMapper.insert(wxCommentReply);
        if (i < 1){
            throw new BusinessException(BaseResponseCode.Wx_COMMENT_ERROR);
        }
    }
}
