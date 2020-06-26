package com.linjr.service;

import org.springframework.web.multipart.MultipartFile;

public interface Order2Service {
    //二维订单导入
    void batchAddData2(String fileName, MultipartFile file) throws Exception;

    //一维订单导入
    void Unidimensional2(String fileName, MultipartFile file) throws Exception;
}
