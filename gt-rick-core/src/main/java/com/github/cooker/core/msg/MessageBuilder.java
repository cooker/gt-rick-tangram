package com.github.cooker.core.msg;

import com.github.cooker.core.RickFileMessage;
import com.github.cooker.core.RickLogMessage;
import com.github.cooker.core.RickMethodMessage;
import com.github.cooker.core.RickType;

import java.util.Map;

/**
 * grant
 * 30/5/2020 9:24 上午
 * 描述：
 */
interface MessageBuilder extends IBuild{

    MessageBuilder clientId(String clientId);

    MessageBuilder router(int router);

    MessageBuilder businessNo(String businessNo);

    MessageBuilder heartMethod(RickLogMessage.logMessage msg, int router);

    MessageBuilder heartMethod(RickLogMessage.logMessage msg);

    MessageBuilder uploadLogMethod(RickLogMessage.logMessage msg);

    MessageBuilder exceptionLogMethod(RickLogMessage.logMessage msg);

    MessageBuilder receiptLogMethod(RickLogMessage.logMessage msg);

    MessageBuilder params(Map<String, String> params);

    MessageBuilder method(String method, RickMethodMessage.methodMessage msg);

    MessageBuilder method(String method);

    MessageBuilder fileMethod(String method, RickFileMessage.fileMessage msg);

    MessageBuilder serialNo(long datacenterId, long machineId);

    MessageBuilder timestamp();

    MessageBuilder type(RickType.messageType type);

}
