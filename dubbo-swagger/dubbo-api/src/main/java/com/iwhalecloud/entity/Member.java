package com.iwhalecloud.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Member", description="会员对象")
public class Member implements Serializable {

    @ApiModelProperty(value = "会员Id")
    private Integer memberId;

    @ApiModelProperty(value = "会员名")
    private String memberName;

    @ApiModelProperty(value = "会员职位")
    private String memberPosition;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPosition() {
        return memberPosition;
    }

    public void setMemberPosition(String memberPosition) {
        this.memberPosition = memberPosition;
    }
}
