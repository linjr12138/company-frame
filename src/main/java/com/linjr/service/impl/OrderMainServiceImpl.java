package com.linjr.service.impl;

import com.linjr.entity.db2.OrderMain;
import com.linjr.exception.BusinessException;
import com.linjr.exception.code.BaseResponseCode;
import com.linjr.mapper.db2.OrderMainMapper;
import com.linjr.service.OrderMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderMainServiceImpl implements OrderMainService {

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Transactional(transactionManager = "db2TransactionManager",rollbackFor = Exception.class)
    @Override
    public OrderMain selectByClientCode(String clientcode) {
        return orderMainMapper.selectByClientCode(clientcode);
    }

    @Transactional(transactionManager = "db2TransactionManager",rollbackFor = Exception.class)
    @Override
    public void ImportCreateOrderMain(String cilentcode) {
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderno(getOrderNO(cilentcode));
        orderMain.setClientcode(cilentcode);
        orderMain.setOrdertype((byte) 0);
        orderMain.setOrderstate((byte) 0);
        orderMain.setIsinvalid(false);
        orderMain.setPdacode(null);
        orderMain.setOrderremark("林景荣导入");
        orderMain.setConfirmman(null);
        orderMain.setCheckdate(null);
        orderMain.setCheckman(null);
        orderMain.setOrderman("");
        orderMain.setOrderdate(new Date());
        orderMain.setWno(new Date());
        orderMain.setReserve1(null);
        orderMain.setReserve2(null);
        orderMain.setReserve3(null);
        int i = orderMainMapper.insertSelective(orderMain);
        if (i != 1){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }
    }

    private String getOrderNO(String cilentcode){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return cilentcode+formatter.format(date);
    }
}
