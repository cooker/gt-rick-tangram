package com.github.cooker.client.utils;

import com.github.cooker.client.ClientApp;

/**
 * grant
 * 1/6/2020 4:28 下午
 * 描述：
 */
public final class CommonUtils {

    public static String clientId(){
        return ClientApp.getSelf().getConf().getString("clientId");
    }

    public static long datacenterId(){
        return ClientApp.getSelf().getConf().getLong("datacenterId");
    }

    public static long machineId(){
        return ClientApp.getSelf().getConf().getLong("machineId");
    }


    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            ;
        }
    }

}
