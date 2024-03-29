package com.linjr.exception;

import com.linjr.exception.code.ResponseCodeInterface;

/**
 * @ClassName: BusinessException
 * TODO:类文件简单描述
 */
public class BusinessException extends RuntimeException {

    /**
     * 提示编码
     */
    private final int code;

    /**
     * 后端提示语
     */
    private final String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResponseCodeInterface responseCodeInterface) {
        this(responseCodeInterface.getCode(), responseCodeInterface.getMsg());
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
