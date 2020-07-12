package com.github.cooker.server.handler;

import com.github.cooker.core.RickMessage;
import com.github.cooker.server.ServerApp;
import com.github.cooker.server.common.ChannelManager;
import io.netty.channel.ChannelHandlerContext;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;

/**
 * grant
 * 1/6/2020 9:58 上午
 * 描述：
 */
@Slf4j
public class HeartChannelBindHandler implements IHandler{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RickMessage.msg rickMessage = (RickMessage.msg) msg;
        API.Try(()->
                ServerApp.getContext().getBean(ChannelManager.class)
                        .addChannel(rickMessage.getClientId(), ctx.channel())
        ).onFailure((e)->{
            log.error("初始化心跳设置 channelManager 失败", e);
        }).get();
    }
}
