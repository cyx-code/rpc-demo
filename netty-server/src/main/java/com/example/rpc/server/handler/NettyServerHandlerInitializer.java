package com.example.rpc.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * @Classname NettyServerHandlerInitializer
 * @Description TODO
 * @Date 2021/4/6 10:22 下午
 * @Author by chenyuxiang
 */
@Component
public class NettyServerHandlerInitializer implements ChannelHandler {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
