package com.iwhalecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * Hello world!
 *
 */
@EnableDubbo
@SpringBootApplication
public class ProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderApp.class, args);
    }
}
