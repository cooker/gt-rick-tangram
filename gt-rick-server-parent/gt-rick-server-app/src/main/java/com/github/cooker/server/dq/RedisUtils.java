package com.github.cooker.server.dq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * grant
 * 30/5/2020 8:19 上午
 * 描述：
 */
@Slf4j
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean checkBlackIp(InetSocketAddress address){
        String host = address.getHostString();
        try {
            String cacheHost = Objects.toString(stringRedisTemplate
                    .opsForHash().get(IContants.BLACK_IP, host));
            if (StringUtils.isNotEmpty(cacheHost) && !"null".equalsIgnoreCase(cacheHost)){
                return true;
            }
        }catch (RuntimeException e) {
            log.error("redis 异常请检查", e);
        }
        return false;
    }
}
