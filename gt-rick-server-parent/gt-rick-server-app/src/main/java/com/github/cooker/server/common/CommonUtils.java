package com.github.cooker.server.common;

import io.netty.channel.Channel;

/**
 * ZoomGrant 2020/5/31 17:40
 */
public class CommonUtils {

    public static final String getClientHost(Channel ch){
        return ch == null ? "null host" : ch.remoteAddress().toString();
    }
}
