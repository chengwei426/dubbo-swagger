package com.iwhalecloud;

import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.iwhalecloud.swagger.dubbo.annotations.EnableDubboSwagger;


// http://127.0.0.1:8010/swagger-dubbo/api-docs
// http://127.0.0.1:8010/distv2/index.html
//dubbo实现类的路径
@EnableDubboSwagger
@DubboComponentScan(basePackages = { "com.iwhalecloud" })
@Configuration
public class SwaggerConfig {

}
