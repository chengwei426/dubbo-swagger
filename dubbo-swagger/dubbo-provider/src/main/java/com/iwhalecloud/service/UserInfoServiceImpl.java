package com.iwhalecloud.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.iwhalecloud.api.IUserInfoService;
import com.iwhalecloud.common.ResponseData;
import com.iwhalecloud.entity.Member;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Override
    public String getUser(Long userId) {
        return "你好";
    }


    @Override
    public List<Member> querymemberDto(Member member) {
        //ResponseData<Member> responseData = new ResponseData<Member>("00000", "operation success");
        if (StringUtils.isNotBlank(member.getMemberName())) {
            member.setMemberName("hello, " + member.getMemberName());
        }

        if (StringUtils.isNotBlank(member.getMemberPosition())) {
            member.setMemberPosition(member.getMemberPosition() + " 3333");
        }

        List<Member> list = new ArrayList<>(2);
        list.add(member);
        return list;

       // responseData.setData(member);
        //return responseData;
    }
}
