package com.github.cooker;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * grant
 * 28/5/2020 9:43 上午
 * 描述：
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new LoggingHandler(LogLevel.DEBUG));
        try {
            ChannelFuture future = bootstrap.bind(7113).sync();
            future = future.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            group.shutdownGracefully();
        }
    }
}
