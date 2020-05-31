package com.github.cooker.client.handler;

import com.github.cooker.client.ClientApp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * ZoomGrant 2020/5/30 15:24
 */
@Slf4j
public class RetryConnectHandler extends ChannelInboundHandlerAdapter {

    private  ClientApp clientApp;

    public RetryConnectHandler(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ch) throws Exception {
        if (clientApp.isState()) {
            ch.channel().close();
            final EventLoop loop = ch.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                    log.info("client reconnect");
                    clientApp.start();
                }
            }, 1L, TimeUnit.SECONDS);
        }
    }
}
