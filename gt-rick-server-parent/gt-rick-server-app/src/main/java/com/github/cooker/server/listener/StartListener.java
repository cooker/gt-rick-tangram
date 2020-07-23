//package com.github.cooker.server.listener;
//
//import com.github.cooker.core.RickMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationStartedEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.EventListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
///**
// * grant
// * 29/5/2020 2:56 下午
// * 描述：
// */
//@Slf4j
////@Component
//public class StartListener implements ApplicationListener<ApplicationStartedEvent> {
//
////    @Autowired
////    StringRedisTemplate redisTemplate;
//    @Autowired
//    KafkaTemplate<String, RickMessage.msg> kafkaTemplate;
//
//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent event) {
//        RickMessage.msg msg = RickMessage.msg.newBuilder()
//                .setBusinessNo("sasadas")
//                .setClientId("123456")
//                .build();
//        kafkaTemplate.send("aaa", msg);
//    }
//}
