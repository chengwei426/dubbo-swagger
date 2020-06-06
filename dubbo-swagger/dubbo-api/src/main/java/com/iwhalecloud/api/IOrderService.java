package com.iwhalecloud.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "订单信息管理")
public interface IOrderService {

    @ApiOperation(value = "获取订单信息", notes = "通过id获取订单信息", response = String.class)
    String getOrderById(@ApiParam(name = "会员id", value = "12", required = true) Long memberId);

}
