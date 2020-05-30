package com.github.cooker.server.dq;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


/**
 * grant
 * 30/5/2020 8:16 上午
 * 描述：
 */
@Slf4j
@Component
public class BlackIpFilterRule implements IpFilterRule {

    @Autowired RedisUtils redisUtils;

    @Override
    public boolean matches(InetSocketAddress inetSocketAddress) {
        boolean isTrue = redisUtils.checkBlackIp(inetSocketAddress);
        if (isTrue){
            log.warn("黑名单拦截: {}", inetSocketAddress.toString());
        }
        return isTrue;
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}
