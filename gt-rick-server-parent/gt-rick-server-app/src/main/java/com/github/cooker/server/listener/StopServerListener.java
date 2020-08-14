package com.github.cooker.server.listener;

import com.github.cooker.server.ServerApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

/**
 * grant
 * 29/5/2020 11:39 上午
 * 描述：
 */
@Slf4j
@Service
public class StopServerListener implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.info("server close 清理线程");
        if (!ServerApp.getBossGroup().isShutdown()){
            ServerApp.getBossGroup().shutdownGracefully();
        }
        if (!ServerApp.getWorkerGroup().isShutdown()){
            ServerApp.getWorkerGroup().shutdownGracefully();
        }
    }

}
