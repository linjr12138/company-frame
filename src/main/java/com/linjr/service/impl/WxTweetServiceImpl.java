package com.linjr.service.impl;

import com.linjr.entity.db1.WxTweet;
import com.linjr.exception.BusinessException;
import com.linjr.exception.code.BaseResponseCode;
import com.linjr.mapper.db1.WxTweetMapper;
import com.linjr.service.WxTweetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxTweetServiceImpl implements WxTweetService {

    private WxTweetMapper wxTweetMapper;

    @Override
    public List<WxTweet> selectAllTweet() {

        return wxTweetMapper.selectAll();
    }

    @Override
    public void addWxTweet(WxTweet wxTweet) {
        int i = wxTweetMapper.insertSelective(wxTweet);
        if (i < 1){
            throw new BusinessException(BaseResponseCode.Wx_RELEASE_ERROR);
        }
    }

    @Override
    public WxTweet selectOneTweet(Integer id) {
        return wxTweetMapper.selectByPrimaryKey(id);
    }
}
