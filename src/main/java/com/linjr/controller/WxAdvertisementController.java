package com.linjr.controller;

import com.linjr.entity.db1.WxAdvertisement;
import com.linjr.service.WxAdvertisementService;
import com.linjr.utils.DataResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wxapi/advertisement")
@Api(tags = "微信广告模块相关接口")
@Slf4j
public class WxAdvertisementController {

    @Autowired
    private WxAdvertisementService wxAdvertisementService;

    @GetMapping()
    public DataResult<List<WxAdvertisement>> selectAllAdvertisement(HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(wxAdvertisementService.selectAll());
        return result;
    }

}
