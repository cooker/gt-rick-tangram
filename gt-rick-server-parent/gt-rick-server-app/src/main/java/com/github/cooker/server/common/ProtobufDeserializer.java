package com.github.cooker.server.common;

import com.github.cooker.core.RickMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * ZoomGrant 2020/5/31 9:39
 */
@Slf4j
public class ProtobufDeserializer implements Deserializer<RickMessage.msg> {

    @Override
    public RickMessage.msg deserialize(String topic, byte[] bytes) {
        log.info("转换>>>>>>>>>>>>>>>>>>");
        try {
            return RickMessage.msg.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            log.error("{} 消息转换失败",topic, e);
        }
        return null;
    }
}
