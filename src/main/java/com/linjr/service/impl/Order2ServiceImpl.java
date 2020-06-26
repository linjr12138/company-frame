package com.linjr.service.impl;

import com.linjr.entity.db2.*;
import com.linjr.exception.BusinessException;
import com.linjr.exception.code.BaseResponseCode;
import com.linjr.mapper.db2.OrderDetailMapper;
import com.linjr.service.*;
import com.linjr.utils.ExcelUtils;
import com.linjr.utils.ReflectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class Order2ServiceImpl implements Order2Service {


    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMainService orderMainService;

    @Autowired
    private BaseProductService baseProductService;

    @Autowired
    private BaseClientService baseClientService;

    @Autowired
    private BaseProductSizeService baseProductSizeService;

    @Transactional(transactionManager = "db2TransactionManager",rollbackFor = Exception.class)
    @Override
    public void batchAddData2(String fileName, MultipartFile file) throws Exception {
        List<BaseProduct> notExistBaseProduct = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new BusinessException(BaseResponseCode.FORMAT_ERROR);
        }
        InputStream is = file.getInputStream();
        List<Object> entityList =  ExcelUtils.importDataFromExcel(new OrderImport(),is,fileName);
        if(entityList.isEmpty()){
            throw new BusinessException(BaseResponseCode.EXECLEMPTY_ERROR);
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Object object : entityList){
            OrderImport orderImport = (OrderImport)object;
            OrderDetail orderDetail = new OrderDetail();
            //判断店铺是否存在
            OrderMain orderMain = getOrderNo(orderImport.getClientcode());
            //以下都有订单的
            //取订单号
            orderDetail.setOrderno(orderMain.getOrderno());
            //取货品，到这里说明货品资料中有这一款色
            BaseProduct baseProduct = getBaseProduct(orderImport.getProdcode(),orderImport.getColorcode(),orderImport.getPattern());
            //判断OrderDetail是否有该货品订量
//            boolean type;
//            OrderDetail orderDetailExist = getOrderDetail(orderMain.getOrderno(),orderImport.getProdcode(),orderImport.getColorcode());
//            //true为订量已经存在，则跟新。false为订量不存在需要插入
//            if (orderDetailExist != null){
//                int k = orderDetailMapper.deleteByOrderNo(orderMain.getOrderno());
//                if (k <=0){
//                    throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
//                }
//            }else{
//                type = false;
//            }
            orderDetail.setDetailuid(UUID.randomUUID().toString());
            BeanUtils.copyProperties(baseProduct,orderDetail);
            orderDetail.setQtysum(Integer.parseInt(orderImport.getQtysum()));
            orderDetail.setAmountsum(new BigDecimal(Integer.parseInt(orderImport.getQtysum())).multiply(baseProduct.getUnitprice()));
            orderDetail.setUploadtime(new Date());
            orderDetail.setIsdeleted(false);
            for (int j=1;j <= 30;j++){
                ReflectionUtils.setPropertyVal("size"+j,orderDetail, dealWithSize(ReflectionUtils.getPropertyVal("size"+j,orderImport).toString()));
            }
            orderDetail.setWno(new Date());
            orderDetail.setSourcecode("林景荣");
            orderDetail.setEdituser("林景荣");
            orderDetail.setUploadversion(0);

            orderDetailList.add(orderDetail);

        }

        int k = orderDetailMapper.deleteByOrderNo(orderDetailList.get(1).getOrderno());
        if (k <=0){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }
        int i = orderDetailMapper.batchInsertOrder(orderDetailList);
        if (i < 1){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }

    }

    @Transactional(transactionManager = "db2TransactionManager",rollbackFor = Exception.class)
    @Override
    public void Unidimensional2(String fileName, MultipartFile file) throws Exception {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new BusinessException(BaseResponseCode.FORMAT_ERROR);
        }
        InputStream is = file.getInputStream();
        List<Object> entityList =  ExcelUtils.importDataFromExcel(new UnidimensionalOrderImport(),is,fileName);
        if(entityList.isEmpty()){
            throw new BusinessException(BaseResponseCode.EXECLEMPTY_ERROR);
        }
        List<BaseProductSize> baseProductSizeList = baseProductSizeService.selectAssociationSize();
        List<UnidimensionalOrderImport> unidimensionalOrderImportList = new ArrayList<>();
        for (Object object : entityList){
            UnidimensionalOrderImport unidimensionalOrderImport = (UnidimensionalOrderImport)object;
            unidimensionalOrderImportList.add(unidimensionalOrderImport);
        }
        for (int i = 0;i<unidimensionalOrderImportList.size();i++){
            for (int k = 0;k<unidimensionalOrderImportList.size();k++){
                if (unidimensionalOrderImportList.get(i).getClientcode().equals(unidimensionalOrderImportList.get(k))){
                    throw new BusinessException(BaseResponseCode.REPECTSHOP_ERROR);
                }
            }
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(getMockOrderDetail());

        for (UnidimensionalOrderImport unidimensionalOrderImport : unidimensionalOrderImportList) {
            boolean type = true;
            for (OrderDetail orderDetail : orderDetailList) {
                if (unidimensionalOrderImport.getProdcode().equals(orderDetail.getProdcode()) &&
                        unidimensionalOrderImport.getColorcode().equals(orderDetail.getColorcode()) &&
                        unidimensionalOrderImport.getPattern().equals(orderDetail.getPattern())) {
                    //orderDetailList里面有该款，那么更新orderDetailList
                    for (BaseProductSize baseProductSize : baseProductSizeList) {
                        if (baseProductSize.getSizeclass().equals(unidimensionalOrderImport.getSizeclass())) {
                            String strSize[] = baseProductSize.getSize().split(",");
                            for (int j = 0; j < strSize.length; j++) {
                                if (unidimensionalOrderImport.getSize().equals(strSize[j])) {
                                    ReflectionUtils.setPropertyVal("size" + (j + 1), orderDetail, dealWithSize(ReflectionUtils.getPropertyVal("qtysum", unidimensionalOrderImport).toString()));
                                }
                            }
                        }
                    }
                    //跟新数量和吊牌额
                    orderDetail.setQtysum(orderDetail.getQtysum() + Integer.parseInt(unidimensionalOrderImport.getQtysum()));
                    orderDetail.setAmountsum(new BigDecimal(orderDetail.getQtysum()).multiply(orderDetail.getUnitprice()));
                    type = false;
                }

            }
            //如果type为true，说明没有跟新orderDetailList，需要插入，false，说明更新说明没有跟新orderDetailList
            if (type) {
                //orderDetailList里面没有该款，那么插入到add（orderDetailList）
                OrderDetail orderDetailImport = new OrderDetail();
                //判断店铺是否存在
                OrderMain orderMain = getOrderNo(unidimensionalOrderImport.getClientcode());
                //取订单号
                orderDetailImport.setOrderno(orderMain.getOrderno());
                //取货品，到这里说明货品资料中有这一款色
                BaseProduct baseProduct = getBaseProduct(unidimensionalOrderImport.getProdcode(), unidimensionalOrderImport.getColorcode(), unidimensionalOrderImport.getPattern());
                BeanUtils.copyProperties(baseProduct, orderDetailImport);
                orderDetailImport.setDetailuid(UUID.randomUUID().toString());
                orderDetailImport.setQtysum(Integer.parseInt(unidimensionalOrderImport.getQtysum()));
                orderDetailImport.setAmountsum(new BigDecimal(orderDetailImport.getQtysum()).multiply(orderDetailImport.getUnitprice()));
                orderDetailImport.setUploadtime(new Date());
                orderDetailImport.setIsdeleted(false);
                orderDetailImport.setWno(new Date());
                orderDetailImport.setSourcecode("林景荣");
                orderDetailImport.setEdituser("林景荣");
                orderDetailImport.setUploadversion(0);
                for (BaseProductSize baseProductSize : baseProductSizeList) {
                    if (baseProductSize.getSizeclass().equals(unidimensionalOrderImport.getSizeclass())) {
                        String strSize[] = baseProductSize.getSize().split(",");
                        for (int j = 0; j < strSize.length; j++) {
                            if (unidimensionalOrderImport.getSize().equals(strSize[j])) {
                                ReflectionUtils.setPropertyVal("size" + (j + 1), orderDetailImport, dealWithSize(ReflectionUtils.getPropertyVal("qtysum", unidimensionalOrderImport).toString()));
                            }
                        }
                    }
                }
                orderDetailList.add(orderDetailImport);
            }
        }
        orderDetailList.remove(0);
        int k = orderDetailMapper.deleteByOrderNo(orderDetailList.get(1).getOrderno());
        if (k < 0){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }
        int z = orderDetailMapper.batchInsertOrder(orderDetailList);
        if (z <= 0){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }
    }

    //通过店铺编号查找订单,如果不存在，测查村是否有该店铺
    private OrderMain getOrderNo(String clientcode){
        //查询店铺是否存在
        BaseClient baseClient = baseClientService.selectByClientCode(clientcode);
        if (baseClient == null){//不存在
            throw new BusinessException(BaseResponseCode.IMPORTBASECLIENTEMPTY_ERROR);
        }else{
            OrderMain orderMain = orderMainService.selectByClientCode(clientcode);
            if (orderMain == null)
                orderMainService.ImportCreateOrderMain(clientcode);
        }
        return orderMainService.selectByClientCode(clientcode);
    }
    //通过款号查找货品是否存在，如果存在，则补全信息，如果不存在，则不导入，且抛出异常
    private BaseProduct getBaseProduct(String prodcode,String colorcode,String pattern){
        BaseProduct baseProduct = baseProductService.selectBaseProductByProdCode(prodcode,colorcode,pattern);
        if (baseProduct == null){
            throw new BusinessException(BaseResponseCode.IMPORTBASEPRODUCTEMPTY_ERROR);
        }
        return baseProduct;
    }

    //判断是否已经有这款的订量
    private OrderDetail getOrderDetail(String orderno,String prodcode,String colorcode){
        return orderDetailMapper.selectByOrderNoProdCodeColorCode(orderno,prodcode,colorcode);
    }

    //判断Size是否为空或者null，或者等于

    private Integer dealWithSize(String size){
        if (size == null || size.equals("0") || size.equals("")){
            return null;
        }
        return Integer.parseInt(size);

    }

    private OrderDetail getMockOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailuid(UUID.randomUUID().toString());
        orderDetail.setOrderno("ABCDEFGHIJK");
        orderDetail.setProdcode("ABCDEFGHIJK");
        return orderDetail;
    }

}
