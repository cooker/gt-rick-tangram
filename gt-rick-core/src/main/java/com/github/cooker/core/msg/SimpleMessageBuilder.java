package com.github.cooker.core.msg;

import com.github.cooker.core.*;
import com.github.cooker.core.utils.MethodContants;
import com.github.cooker.core.utils.SnowFlakeFactory;
import com.google.protobuf.Timestamp;

import java.util.Map;

/**
 * grant
 * 30/5/2020 9:38 上午
 * 描述：
 */
public class SimpleMessageBuilder implements MessageBuilder{
    RickMessage.msg.Builder builder = RickMessage.msg.newBuilder();

    public static SimpleMessageBuilder builder(){
        return new SimpleMessageBuilder()
                .serialNo(0L, 0L)
                .timestamp();
    }

    public static SimpleMessageBuilder builder(long datacenterId, long machineId){
        return new SimpleMessageBuilder()
                .serialNo(datacenterId, machineId)
                .timestamp();
    }

    @Override
    public MessageBuilder clientId(String clientId) {
        builder.setClientId(clientId);
        return this;
    }

    public SimpleMessageBuilder businessNo(String businessNo) {
        if (businessNo == null) throw new NullPointerException("businessNo is null");
        builder.setBusinessNo(businessNo);
        return this;
    }

    public SimpleMessageBuilder heartMethodBy0(RickLogMessage.logMessage msg) {
        this.heartMethod(msg, 0);
        return this;
    }

    public SimpleMessageBuilder heartMethodBy1(RickLogMessage.logMessage msg) {
        this.heartMethod(msg, 1);
        return this;
    }

    public SimpleMessageBuilder heartMethod(RickLogMessage.logMessage msg, int router) {
        builder.setMethod(MethodContants.HEART);
        builder.setRouter(router);
        builder.setType(RickType.messageType.LOG);
        builder.setLogBody(msg);
        return this;
    }

    @Override
    public MessageBuilder heartMethod(RickLogMessage.logMessage msg) {
        builder.setLogBody(msg);
        return this;
    }

    public SimpleMessageBuilder uploadLogMethod(RickLogMessage.logMessage msg) {
        builder.setMethod(MethodContants.HEART);
        builder.setRouter(0);
        builder.setType(RickType.messageType.LOG);
        builder.setLogBody(msg);
        return this;
    }

    public SimpleMessageBuilder exceptionLogMethod(RickLogMessage.logMessage msg) {
        builder.setMethod(MethodContants.EXCEPTION);
        builder.setRouter(0);
        builder.setType(RickType.messageType.LOG);
        builder.setLogBody(msg);
        return this;
    }

    public SimpleMessageBuilder receiptLogMethod(RickLogMessage.logMessage msg) {
        builder.setMethod(MethodContants.RECEIPT);
        builder.setRouter(0);
        builder.setType(RickType.messageType.LOG);
        builder.setLogBody(msg);
        return this;
    }

    public SimpleMessageBuilder params(Map<String, String> params) {
        builder.putAllParams(params);
        return this;
    }

    public SimpleMessageBuilder method(String method, RickMethodMessage.methodMessage msg) {
        builder.setMethod(method);
        builder.setRouter(0);
        builder.setType(RickType.messageType.METHOD);
        builder.setMethodBody(msg);
        return this;
    }

    public SimpleMessageBuilder fileMethod(String method, RickFileMessage.fileMessage msg) {
        builder.setMethod(method);
        builder.setRouter(0);
        builder.setType(RickType.messageType.FILE);
        builder.setFileBody(msg);
        return this;
    }

    public SimpleMessageBuilder serialNo(long datacenterId, long machineId){
        builder.setSerialNo(SnowFlakeFactory.getInstance(datacenterId, machineId).next());
        return this;
    }

    public SimpleMessageBuilder timestamp(){
        long millis = System.currentTimeMillis();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000)).build();
        builder.setTimestamp(timestamp);
        return this;
    }

    @Override
    public SimpleMessageBuilder router(int router) {
        builder.setRouter(router);
        return this;
    }

    @Override
    public SimpleMessageBuilder method(String method) {
        builder.setMethod(method);
        return this;
    }

    @Override
    public MessageBuilder type(RickType.messageType type) {
        builder.setType(type);
        return this;
    }

    @Override
    public RickMessage.msg build() {
        return builder.build();
    }
}
