package com.github.cooker.server.common;


import io.netty.channel.Channel;

/**
 * ZoomGrant 2020/5/31 16:56
 */
public interface ChannelManager {

    boolean addChannel(String clientId, Channel channel);

    void removeChannel(String clientId, Channel channel);

    boolean removeChannel(Channel channel);

    void clearChannel(String clientId);

    Channel getChannel(String clientId);

}
