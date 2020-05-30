package com.github.cooker.server;

import com.github.cooker.core.RickMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * grant
 * 27/5/2020 3:45 下午
 * 描述：
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
//            ctx.fireChannelRead() 消息下发
            RickMessage.msg rickMessage = (RickMessage.msg) msg;
            if(StringUtils.isEmpty(rickMessage.getClientId())){
                log.info("clientId 为空直接丢弃");
            }else {
                log.info("收到消息>>>>>>>>>>>>{}", rickMessage.getClientId());
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常", cause);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        log.info("连接断开：{}", ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("连接...：{}", ctx.channel());
    }
}
