package com.linjr.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.linjr.entity.db1.WxClient;
import com.linjr.exception.BusinessException;
import com.linjr.exception.code.BaseResponseCode;
import com.linjr.service.WxClientService;
import com.linjr.utils.DataResult;
import com.linjr.vo.req.WxUserInfoReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wxapi")
@Api(tags = "微信客户模块相关接口")
@Slf4j
public class WxClientController {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private WxClientService wxClientService;
    /**
     * 微信登录接口
     * @param WxUserInfoReqVO
     * @param request
     */
    @PostMapping(value = "/login_wx")
    public DataResult loginWx(@RequestBody WxUserInfoReqVO WxUserInfoReqVO, HttpServletRequest request) {
        String code = WxUserInfoReqVO.getCode();
        WxClient wxClient = WxUserInfoReqVO.getWxClient();
        if (code == null || wxClient == null) {
            log.error("code is null");

            throw new BusinessException(BaseResponseCode.WX_LOGIN_ERROR);
        }
        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            log.error("login fail by wx");
            e.printStackTrace();
        }
        if (sessionKey == null || openId == null) {
            throw new BusinessException(BaseResponseCode.WX_LOGIN_ERROR);
        }
        return DataResult.success();
        //登录成功处理
    }

    @ApiOperation(value = "查找某一推文")
    @GetMapping(value = "/selectOneClient/{clientId}")
    public DataResult<WxClient> selectWxClientById(@PathVariable("clientId") Integer clientId, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(wxClientService.selectById(clientId));
        return result;
    }
}
