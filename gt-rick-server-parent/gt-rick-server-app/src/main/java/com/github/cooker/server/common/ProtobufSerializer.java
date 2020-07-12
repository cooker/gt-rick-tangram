package com.github.cooker.server.common;

import com.github.cooker.core.RickMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

/**
 * ZoomGrant 2020/5/31 9:38
 */
@Slf4j
public class ProtobufSerializer implements Serializer<RickMessage.msg> {
    @Override
    public byte[] serialize(String topic, RickMessage.msg msg) {
        log.info("序列化");
        return msg.toByteArray();
    }
}
