package com.linjr;


import com.linjr.entity.db1.Region;
import com.linjr.entity.db1.SysRole;
import com.linjr.entity.db2.*;
import com.linjr.mapper.db2.BaseProductMapper;
import com.linjr.mapper.db2.BaseProductSizeMapper;
import com.linjr.mapper.db2.OrderDetailMapper;
import com.linjr.mapper.db2.PsoDelTestMapper;
import com.linjr.mapper.db1.RegionMapper;
import com.linjr.mapper.db1.SysRoleMapper;
import com.linjr.mapper.db1.SysUserMapper;
import com.linjr.service.*;
import com.linjr.utils.ReflectionUtils;
import com.linjr.vo.req.BaseProductPageReqVO;
import com.linjr.vo.req.OrderPageReqVO;
import com.linjr.vo.req.RolePageReqVO;
import com.linjr.vo.resp.BaseProDuctColorRespVO;
import com.linjr.vo.resp.OrderRespVO;
import com.linjr.vo.resp.PageVO;
import com.linjr.vo.resp.XMSelectNameValueRespVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyFrameApplicationTests {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private PsoDelTestMapper psoDelTestMapper;

    @Autowired
    private InsertTaskServer insertTaskServer;

    @Autowired
    private BaseClientShopService baseClientShopService;

    @Autowired
    private BaseClientService baseClientService;

    @Autowired
    private BaseProductService baseProductService;

    @Autowired
    private VAllOrderDiscountService vAllOrderDiscountService;

    @Autowired
    private BaseProductMapper baseProductMapper;

    @Autowired
    private OrderMainService orderMainService;

    @Autowired
    private  BaseProductSizeService baseProductSizeService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedis() {
        redisService.set("redisTest", "redis test");
        System.out.println(redisService.get("redisTest"));
    }

    @Test
    public void testSelectrole() {
        RolePageReqVO vo = new RolePageReqVO();
        vo.setPageNum(0);
        vo.setPageSize(10);
        List<SysRole> list = new ArrayList<>();
        list = sysRoleMapper.selectAll(vo);
        System.out.println(list);

    }

    @Test
    public void regiontest() {
        List<Region> list = regionService.regionInfo();
        System.out.println(list);
    }

    @Test
    public void regionselectAlltest() {
        List<Region> list = regionMapper.selectAll();
        System.out.println(list);
    }

    @Test
    public void select1000Wtest() {

        insertTaskServer.insertBigDate();
//        insertTaskServer.insertBigDate2();
    }

    @Test
    public void testsqlserver(){
        baseClientShopService.selectAll();
    }

    @Test
    public void testBaseProduct(){
        BaseProductPageReqVO vo = new BaseProductPageReqVO();
        vo.setPageNum(1);
        vo.setPageSize(100);
        PageVO<BaseProduct> baseProductPageVO = baseProductService.baseProductPageInfo(vo);
        System.out.println(baseProductPageVO);

    }

    @Test
    public void getOrderTest(){
        OrderDetail orderDetail1 = new OrderDetail();
        OrderDetail orderDetail2 = new OrderDetail();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetail1.setDetailuid(UUID.randomUUID().toString());
        orderDetail2.setDetailuid(UUID.randomUUID().toString());
        orderDetail1.setOrderno("1000320200608103846");
        orderDetail2.setOrderno("1000420200608103846");
        BaseProduct baseProduct = new BaseProduct();
        baseProduct = baseProductService.selectBaseProductByProdCode("CDL1B077","N20","");
        BeanUtils.copyProperties(baseProduct,orderDetail1);
        BeanUtils.copyProperties(baseProduct,orderDetail2);
        orderDetail1.setQtysum(8);
        orderDetail1.setAmountsum(new BigDecimal(3992));
        orderDetail1.setUploadtime(new Date());
        orderDetail1.setIsdeleted(false);

        orderDetail1.setSize1(1);
        orderDetail1.setSize2(2);
        orderDetail1.setSize3(2);
        orderDetail1.setSize4(2);
        orderDetail1.setSize5(1);

        orderDetail1.setWno(new Date());
        orderDetail1.setSourcecode("林景荣");
        orderDetail1.setEdituser("林景荣");
        orderDetail1.setUploadversion(0);

        orderDetailList.add(orderDetail1);

        orderDetail2.setQtysum(8);
        orderDetail2.setAmountsum(new BigDecimal(3992));
        orderDetail2.setUploadtime(new Date());
        orderDetail2.setIsdeleted(false);

        orderDetail2.setSize1(1);
        orderDetail2.setSize2(2);
        orderDetail2.setSize3(2);
        orderDetail2.setSize4(2);
        orderDetail2.setSize5(1);

        orderDetail2.setWno(new Date());
        orderDetail2.setSourcecode("林景荣");
        orderDetail2.setEdituser("林景荣");
        orderDetail2.setUploadversion(0);

        orderDetailList.add(orderDetail2);

        System.out.println(orderDetailList);

        orderDetailMapper.batchInsertOrder(orderDetailList);


    }

    @Test
    public void testselect(){
        List<BaseProDuctColorRespVO> list = baseProductService.selectOneList();
        System.out.println("changdu:"+list.size());
        System.out.println("123123123"+list);
    }

    @Test
    public void testsize(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(formatter.format(date));

    }

    @Test
    public void test() throws Exception{
        //获取Class对象
        Class perClass = Class.forName("com.linjr.entity.db2.OrderDetail");
        //获取字段
        System.out.println("获取所有字段");
        Field[] fieldArray = perClass.getDeclaredFields();
        for (Field field : fieldArray) {
            System.out.println(field);
        }
        System.out.println("获取所有共有字段");
        fieldArray = perClass.getFields();
        for (Field field : fieldArray) {
            System.out.println(field);
        }
//        System.out.println("获取所有共有字段并调用");
//        Field f = perClass.getField("colorcode");
//        System.out.println(f);
//        //获取Person对象
//        Object per = perClass.getConstructor().newInstance();
//        //为字段设置值
//        f.set(per, "反射");
//        //验证
//        Person person = (Person)per;
//        System.out.println("验证名字:" +person.name);
//
//        System.out.println("--------------------------------------------");
//
//        //2.获取所有公有构造方法
//        System.out.println("所有公有构造方法");
//        Constructor[] conArray = perClass.getConstructors();
//        for(Constructor c : conArray){
//            System.out.println(c);
//        }
//
//
//        System.out.println("所有的构造方法(包括：私有、受保护、默认、公有)");
//        conArray = perClass.getDeclaredConstructors();
//        for(Constructor c : conArray){
//            System.out.println(c);
//        }
//
//        System.out.println("获取公有、无参的构造方法");
//        Constructor con = perClass.getConstructor();
//        System.out.println("con = " + con);
//        //调用构造方法
//        Object obj = con.newInstance();
//
//        System.out.println("获取私有构造方法，并调用");
//        con = perClass.getDeclaredConstructor(char.class);
//        System.out.println(con);
//        //调用构造方法
//        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
//        obj = con.newInstance('男');
//
//        System.out.println("--------------------------------------------");
//
//        //获取所有公有方法
//        System.out.println("获取所有的”公有“方法");
//        perClass.getMethods();
//        Method[] methodArray = perClass.getMethods();
//        for(Method m : methodArray){
//            System.out.println(m);
//        }
//
//        //获取所有方法
//        System.out.println("获取所有的方法，包括私有的");
//        methodArray = perClass.getDeclaredMethods();
//        for(Method m : methodArray){
//            System.out.println(m);
//        }
//
//        //获取特定的共有方法
//        System.out.println("获取公有的eat()方法");
//        Method m = perClass.getMethod("eat", String.class);
//        System.out.println(m);
//        //实例化一个Student对象
//        Object obj2 = perClass.getConstructor().newInstance();
//        m.invoke(obj2, "反射");
//
//
//        System.out.println("获取私有的study()方法");
//        m = perClass.getDeclaredMethod("study", int.class);
//        System.out.println(m);
//
//
//        m.setAccessible(true);//解除私有限定
//        Object result = m.invoke(obj2, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
//        System.out.println("返回值：" + result);
    }


    @Test
    public void reflectionTest() throws Exception{
        OrderDetail orderDetail = new OrderDetail();
        int i = 0;
        ReflectionUtils.setFieldValue(orderDetail,"size"+(i+1),1);
        UnidimensionalOrderImport unidimensionalOrderImport = new UnidimensionalOrderImport();
        unidimensionalOrderImport.setSize("1");
        System.out.println("size1:"+ReflectionUtils.getFieldValue(orderDetail,"size1"));

    }

    @Test
    public void testR(){
        OrderImport orderImport = new OrderImport();
        OrderDetail orderDetail = new OrderDetail();
        for (int i = 1;i<=30;i++){
            ReflectionUtils.invokeSetterMethod(orderImport,"size"+i,String.valueOf(i));
            ReflectionUtils.invokeSetterMethod(orderDetail,"size"+i,Integer.valueOf(orderImport.getSize1()));
            System.out.println("size"+i+":"+ReflectionUtils.invokeGetterMethod(orderDetail,"size"+i));
        }
    }

    @Test
    public void test123(){
        List<BaseProductSize> baseProductSizeList = baseProductSizeService.selectAssociationSize();
        for (BaseProductSize baseProductSize : baseProductSizeList) {
            System.out.println(baseProductSize);
                String strSize[] = baseProductSize.getSize().split(",");
                for (int i = 0 ;i<strSize.length;i++){
                    if (strSize[i].equals("") || strSize[i].isEmpty()){
                        strSize[i]=",";
                    }
                    System.out.println(baseProductSize.getSizeclass()+":"+" "+"size"+(i+1)+":"+strSize[i]);
                }
            System.out.println("--------------------------------------------------");
        }
    }




}
