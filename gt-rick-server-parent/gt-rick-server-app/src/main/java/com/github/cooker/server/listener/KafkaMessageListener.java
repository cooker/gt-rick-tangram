package com.github.cooker.server.listener;

import com.github.cooker.core.RickMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.stereotype.Component;

/**
 * ZoomGrant 2020/5/30 17:35
 */
@Slf4j
//@Component
public class KafkaMessageListener {

    @KafkaListener(id = "${server.kafka.id}", topics = "${server.kafka.topic}")
    @KafkaHandler
    public void sa(ConsumerRecord<String, RickMessage.msg> msg, Consumer consumer){
            log.info("kafka >>> {}", msg.value().getBusinessNo());
            consumer.commitSync();
//            throw  new RuntimeException("sasa");
    }
}
