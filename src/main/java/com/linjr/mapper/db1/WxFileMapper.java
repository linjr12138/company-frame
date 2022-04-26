package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxFile;

public interface WxFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxFile record);

    int insertSelective(WxFile record);

    WxFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxFile record);

    int updateByPrimaryKey(WxFile record);
}