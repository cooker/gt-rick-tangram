package com.github.cooker.client.listener;

import com.github.cooker.client.ClientApp;
import com.github.cooker.client.nick.HeartTasker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * grant
 * 1/6/2020 5:01 下午
 * 描述：
 */
@Slf4j
public class StartedClientListener implements ApplicationListener<ApplicationStartedEvent> {
    Thread th;
    ClientApp clientApp = null;

    public StartedClientListener(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("启动心跳定时器...");
        th = new Thread(()->{
            new HeartTasker(clientApp).accept("heart");
        });
        th.setDaemon(true);
        th.start();
    }

}
