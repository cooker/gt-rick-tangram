package com.github.cooker.server.dq;

/**
 * grant
 * 29/5/2020 5:56 下午
 * 描述：
 */
public interface IContants {

    //黑名单
    String BLACK_IP = "blackIp";

    //用户接收消息队列
    String QUEUE_CLIENT_GET_ID = "queue:client:get:";

    //用户发送消息队列
    String QUEUE_CLIENT_SEND_ID = "queue:client:send:";

    //规则
    String RULE = "rule:{clientId}:";
}
