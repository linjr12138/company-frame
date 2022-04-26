package com.linjr.vo.req;

import com.linjr.entity.db1.WxClient;
import lombok.Data;

@Data
public class WxUserInfoReqVO {

    private String code;

    private WxClient wxClient;
}
