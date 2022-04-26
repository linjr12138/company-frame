package com.linjr.controller;

import com.linjr.entity.db1.WxTweet;
import com.linjr.service.WxTweetService;
import com.linjr.utils.DataResult;
import com.linjr.vo.req.WxTweetReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wxapi/tweet")
@Api(tags = "微信推文模块相关接口")
@Slf4j
public class WxTweetController {

    @Autowired
    private WxTweetService wxTweetService;


    @ApiOperation(value = "新增推文")
    @PostMapping(value = "/add")
    public DataResult addTweet(@RequestBody WxTweetReqVO wxTweetReqVO, HttpServletRequest request){
        DataResult result = DataResult.success();
        wxTweetService.addWxTweet(wxTweetReqVO);
        return result;
    }

    @ApiOperation(value = "查找全部推文")
    @GetMapping(value = "/selectAllTweet")
    public DataResult<List<WxTweet>> selectAllTweet( HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(wxTweetService.selectAllTweet());
        return result;
    }

    @ApiOperation(value = "查找某一推文")
    @GetMapping(value = "/selectOneTweet/{tweetId}")
    public DataResult<List<WxTweet>> selectOneTweet(@PathVariable("tweetId") Integer tweetId, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(wxTweetService.selectOneTweet(tweetId));
        return result;
    }

}
