package com.linjr.service.impl;

import com.linjr.entity.db1.WxClient;
import com.linjr.mapper.db1.WxClientMapper;
import com.linjr.service.WxClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxClientServiceImpl implements WxClientService {

    @Autowired
    private WxClientMapper wxClientMapper;


    @Override
    public WxClient selectById(Integer id) {
        return wxClientMapper.selectByPrimaryKey(id);
    }
}
