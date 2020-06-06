package com.iwhalecloud.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ResponseData", description="响应体")
public class ResponseData<T> extends BaseResponse {

    // 响应的消息体
    @ApiModelProperty(value = "响应体业务数据")
    private T data;

    public ResponseData() {
    }

    public ResponseData(String resCode, String resMessage) {
        super(resCode, resMessage);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
