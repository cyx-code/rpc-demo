package com.example.rpc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Classname NettyServerApplication
 * @Description start up class
 * @Date 2021/4/6 9:39 下午
 * @Author by chenyuxiang
 */
public class NettyServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(NettyServerApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
