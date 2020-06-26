package com.linjr.service.impl;

import com.linjr.entity.db2.BaseClientShop;
import com.linjr.mapper.db2.BaseClientShopMapper;
import com.linjr.service.BaseClientShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BaseClientShopServiceImpl implements BaseClientShopService {

    @Autowired
    private BaseClientShopMapper baseClientShopMapper;

    @Transactional(transactionManager = "db2TransactionManager")
    @Override
    public List<BaseClientShop> selectAll() {
        return baseClientShopMapper.selectAll();
    }
}
