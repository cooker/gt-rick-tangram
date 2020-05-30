package com.github.cooker.core.utils;

import com.github.cooker.core.RickMessage;
import com.google.protobuf.Timestamp;

/**
 * grant
 * 27/5/2020 2:33 下午
 * 描述：
 */
public final class RickMessageBuilder {

    RickMessage.msg.Builder builder = RickMessage.msg.newBuilder();

    public static RickMessageBuilder builder(long datacenterId, long machineId){
        return new RickMessageBuilder()
                .serialNo(datacenterId, machineId)
                .timestamp();
    }

    protected RickMessageBuilder serialNo(long datacenterId, long machineId){
        builder.setSerialNo(SnowFlakeFactory.getInstance(datacenterId, machineId).next());
        return this;
    }

    protected RickMessageBuilder timestamp(){
        long millis = System.currentTimeMillis();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000)).build();
        builder.setTimestamp(timestamp);
        return this;
    }
}
