package com.linjr.mapper.db1;

import com.linjr.entity.db1.WxAdvertisement;

import java.util.List;

public interface WxAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxAdvertisement record);

    int insertSelective(WxAdvertisement record);

    WxAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxAdvertisement record);

    int updateByPrimaryKey(WxAdvertisement record);

    List<WxAdvertisement> selectAll();
}