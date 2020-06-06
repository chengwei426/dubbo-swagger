package com.iwhalecloud.api;

import java.util.List;
import com.alibaba.dubbo.config.annotation.Service;
import com.iwhalecloud.common.ResponseData;
import com.iwhalecloud.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "用户管理")
public interface IUserInfoService {

    @ApiOperation(value = "获取用户信息", notes = "通过id取用户名", response = String.class)
    String getUser(@ApiParam(name = "用户id", value ="12", required = true) Long userIdd);

    @ApiOperation(value = "查询会员列表信息", notes = "通过条件取会员列表", response = Member.class, responseContainer = "List")
    List<Member> querymemberDto(@ApiParam(value = "会员信息") Member member);
}
