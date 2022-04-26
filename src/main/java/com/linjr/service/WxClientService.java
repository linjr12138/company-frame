package com.linjr.service;

import com.linjr.entity.db1.WxClient;

public interface WxClientService {

    /**
     * 查找客户详情
     * @param id
     * @return
     */
    WxClient selectById(Integer id);

}
