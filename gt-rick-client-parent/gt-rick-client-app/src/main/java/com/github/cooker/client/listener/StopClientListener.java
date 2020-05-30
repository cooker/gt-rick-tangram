package com.github.cooker.client.listener;

import com.github.cooker.client.ClientApp;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * grant
 * 30/5/2020 12:09 下午
 * 描述：
 */
public class StopClientListener implements ApplicationListener<ContextStoppedEvent> {
    ClientApp clientApp;

    public StopClientListener(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        clientApp.setStateStop();

    }
}
