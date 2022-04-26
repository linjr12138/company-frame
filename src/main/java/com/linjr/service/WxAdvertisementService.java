package com.linjr.service;

import com.linjr.entity.db1.WxAdvertisement;

import java.util.List;

public interface WxAdvertisementService {

    /**
     * 查找所有广告
     * @return
     */
    List<WxAdvertisement> selectAll();

}
