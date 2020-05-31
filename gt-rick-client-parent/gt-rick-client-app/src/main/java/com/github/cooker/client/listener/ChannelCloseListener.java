package com.github.cooker.client.listener;

import com.github.cooker.client.ClientApp;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * ZoomGrant 2020/5/30 14:38
 */
@Slf4j
public class ChannelCloseListener implements ChannelFutureListener {
    ClientApp clientApp;

    public ChannelCloseListener(ClientApp app){
        this.clientApp = app;
    }

    @Override
    public void operationComplete(ChannelFuture ch) throws Exception {
        if (!ch.isSuccess() && clientApp.isState()) {
            ch.channel().close();
            final EventLoop loop = ch.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                    log.info("client reconnect");
                    clientApp.start();
                }
            }, 1L, TimeUnit.SECONDS);
        }else {
            log.info("client connect success");
        }
    }
}
