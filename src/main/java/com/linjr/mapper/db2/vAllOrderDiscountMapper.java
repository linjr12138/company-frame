package com.linjr.mapper.db2;

import com.linjr.entity.db2.VAllOrderDiscount;
import com.linjr.vo.req.BaseProductPageReqVO;
import com.linjr.vo.req.OrderPageReqVO;
import com.linjr.vo.resp.OrderRespVO;

import java.util.List;

public interface vAllOrderDiscountMapper {
    List<VAllOrderDiscount> getAllOrder();

    List<VAllOrderDiscount> selectAll(OrderPageReqVO vo);

}