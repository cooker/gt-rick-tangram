package com.github.cooker.client;

import com.github.cooker.core.RickMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * grant
 * 1/6/2020 5:12 下午
 * 描述：
 */
public interface RickClient {

    boolean isState();

    Bootstrap getBootstrap();

    void setChannel(Channel channel);

    EventLoopGroup getGroup();

    PropertiesConfiguration getConf();

    boolean sendMessage(RickMessage.msg msg);
}
