package com.linjr.controller;

import com.linjr.entity.db1.WxCommentReply;
import com.linjr.entity.db1.WxTweetComment;
import com.linjr.service.WxTweetCommentService;
import com.linjr.utils.DataResult;
import com.linjr.vo.req.WxCommentReplyReqVO;
import com.linjr.vo.req.WxTweetCommentReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wxapi/comment")
@Api(tags = "微信评论模块相关接口")
@Slf4j
public class WxTweetCommentController {

    @Autowired
    private WxTweetCommentService wxTweetCommentService;


    @ApiOperation(value = "新增推文评论")
    @PostMapping(value = "/add")
    public DataResult addCommentByTweetId(@RequestBody WxTweetCommentReqVO wxTweetCommentReqVO , HttpServletRequest request){
        wxTweetCommentService.addComment(wxTweetCommentReqVO);
        DataResult result = DataResult.success();
        return result;
    }

    @ApiOperation(value = "新增推文评论回复")
    @PostMapping(value = "/addReply")
    public DataResult addCommentReplyByCommentId(@RequestBody WxCommentReplyReqVO wxCommentReplyReqVO,HttpServletRequest request){
        wxTweetCommentService.addCommentReply(wxCommentReplyReqVO);
        DataResult result = DataResult.success();
        return result;
    }

}
