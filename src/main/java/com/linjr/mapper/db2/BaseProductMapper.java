package com.linjr.mapper.db2;



import com.linjr.entity.db2.BaseProduct;
import com.linjr.vo.req.BaseProductPageReqVO;
import com.linjr.vo.resp.BaseProDuctColorRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseProductMapper {
    int deleteByPrimaryKey(@Param("prodcode") String prodcode, @Param("colorcode") String colorcode, @Param("pattern") String pattern);

    int insert(BaseProduct record);

    int insertSelective(BaseProduct record);

    BaseProduct selectByPrimaryKey(@Param("prodcode") String prodcode, @Param("colorcode") String colorcode, @Param("pattern") String pattern);

    int updateByPrimaryKeySelective(BaseProduct record);

    int updateByPrimaryKeyWithBLOBs(BaseProduct record);

    int updateByPrimaryKey(BaseProduct record);

    List<BaseProduct> selectAll(BaseProductPageReqVO vo);

    List<BaseProduct> selectAll2();

    List<String> selectOneList();


}