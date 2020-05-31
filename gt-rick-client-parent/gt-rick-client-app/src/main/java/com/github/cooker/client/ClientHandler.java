package com.github.cooker.client;

import com.github.cooker.core.RickMessage;
import com.github.cooker.core.utils.MethodContants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * grant
 * 27/5/2020 5:10 下午
 * 描述：
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        RickMessage.msg msg = RickMessage.msg.newBuilder()
                .setClientId("123456")
                .setMethod(MethodContants.HEART)
                .setRouter(0)
                .build();
        ctx.channel().writeAndFlush(msg);
    }
}
