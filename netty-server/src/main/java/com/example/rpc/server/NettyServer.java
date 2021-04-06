package com.example.rpc.server;

import com.example.rpc.server.handler.NettyServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @Classname NettyServer
 * @Description server
 * @Date 2021/4/6 9:40 下午
 * @Author by chenyuxiang
 */
@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Value("rpc.server.port")
    private Integer port;

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * boss thread group,used for the server to receive client requests
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * worker thread group,used for the server to receive the data read and write from the client
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Channel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(nettyServerHandlerInitializer);
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            channel = future.channel();
            logger.info("start server, port: {}", port);
        }
    }

    @PreDestroy
    public void shutDown() {
        if (channel != null) {
            channel.close();
        }
        // shutdown two thread group
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
