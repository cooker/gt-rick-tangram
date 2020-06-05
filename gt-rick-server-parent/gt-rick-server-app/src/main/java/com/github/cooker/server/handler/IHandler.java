package com.github.cooker.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

/**
 * grant
 * 1/6/2020 9:58 上午
 * 描述：
 */
public interface IHandler extends ChannelInboundHandler {

    @Override
    default void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    default void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    default void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }

    @Override
    default void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    default void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }
}
