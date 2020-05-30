package com.github.cooker;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * grant
 * 28/5/2020 9:43 上午
 * 描述：
 */
public class NettyClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress("localhost", 7113)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println(">>>>> 消息发送..");
                        ctx.channel().writeAndFlush("sasaas");
                    }
                });

        try {
            Channel channel = bootstrap.connect().sync().channel();
            ChannelFuture future = channel.closeFuture().sync();
            future.addListener(ChannelFutureListener.CLOSE);
        } catch (InterruptedException e) {

        } finally {
            group.shutdownGracefully();
        }
    }
}
