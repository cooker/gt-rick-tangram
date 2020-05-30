package com.github.cooker.server.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * grant
 * 29/5/2020 2:56 下午
 * 描述：
 */
@Slf4j
@Component
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
//        log.info(">>>>");
//        redisTemplate.opsForHash().put("a", "ssss", "s");
    }
}
