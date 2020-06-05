package com.github.cooker.core.msg;

import com.github.cooker.core.RickLogMessage;
import com.github.cooker.core.RickMessage;
import com.github.cooker.core.RickType;
import com.github.cooker.core.utils.MethodContants;

import java.util.Map;
import java.util.function.Function;

/**
 * grant
 * 1/6/2020 11:44 上午
 * 描述：
 */
public final class RickHeartLogMessageBuilder implements IBuild{
    MessageBuilder builder = null;

    public static final Function<String, RickLogMessage.logMessage> factory = (msg)->
         RickLogMessage.logMessage.newBuilder().setMsg(msg)
                .setType(0)
                .build();

    public static final RickLogMessage.logMessage NOP = factory.apply("ring");

    protected RickHeartLogMessageBuilder() {
        builder = new SimpleMessageBuilder();
    }

    public RickHeartLogMessageBuilder create(String clientId){
        return this.create(clientId, 1);
    }

    public RickHeartLogMessageBuilder create0(String clientId){
        return this.create(clientId, 0);
    }

    public RickHeartLogMessageBuilder create(String clientId, int router){
        return this.create(clientId, 0L,0L, router);
    }

    public RickHeartLogMessageBuilder create(String clientId,
                                             long datacenterId, long machineId,
                                             int router){
        builder.timestamp();
        builder.method(MethodContants.HEART);
        builder.clientId(clientId);
        builder.serialNo(datacenterId,machineId);
        builder.businessNo(clientId);
        builder.router(router);
        builder.type(RickType.messageType.LOG);
        return this;
    }

    public RickHeartLogMessageBuilder params(Map<String, String> params){
        builder.params(params);
        return this;
    }

    public RickHeartLogMessageBuilder msg(RickLogMessage.logMessage msg){
        builder.heartMethod(msg);
        return this;
    }

    @Override
    public RickMessage.msg build() {
        return builder.build();
    }
}