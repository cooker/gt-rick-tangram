package com.github.cooker.server;

import com.github.cooker.core.RickMessage;
import com.github.cooker.core.utils.MethodContants;
import com.github.cooker.server.dq.ChannelManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * grant
 * 27/5/2020 3:45 下午
 * 描述：
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    ServerApp serverApp;

    public ServerHandler(ServerApp serverApp) {
        this.serverApp = serverApp;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
//            ctx.fireChannelRead() 消息下发
            RickMessage.msg rickMessage = (RickMessage.msg) msg;
            if (StringUtils.isEmpty(rickMessage.getClientId())) {
                log.info("clientId 为空直接丢弃");
            } else if (MethodContants.HEART.equals(rickMessage.getMethod()) && rickMessage.getRouter() == 0){
                log.info("收到初始化心跳>>>>>>>>>>>>{}", rickMessage.getClientId());
                API.Try(()->
                    ServerApp.getContext().getBean(ChannelManager.class).addChannel(rickMessage.getClientId(), ctx.channel())
                ).onFailure((e)->{
                   log.error("spring 容器获取 channelManager 失败", e);
                }).get();
                //权限判断
            }else {

            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("检查 {} 发生异常 {}", ctx.channel(), cause.getMessage());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        log.info("断开：{}", ctx.channel());
        API.Try(()->
                ServerApp.getContext().getBean(ChannelManager.class).removeChannel(ctx.channel())
        ).onFailure((e)->{
            log.error("spring 容器获取 channelManager 失败", e);
        }).get();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("连接...：{}", ctx.channel());
    }
}
