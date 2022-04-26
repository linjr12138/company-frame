package com.linjr.service.impl;

import com.linjr.entity.db1.WxAdvertisement;
import com.linjr.mapper.db1.WxAdvertisementMapper;
import com.linjr.service.WxAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxAdvertisementServiceImpl implements WxAdvertisementService {

    @Autowired
    private WxAdvertisementMapper wxAdvertisementMapper;

    @Override
    public List<WxAdvertisement> selectAll() {
        return wxAdvertisementMapper.selectAll();
    }
}
