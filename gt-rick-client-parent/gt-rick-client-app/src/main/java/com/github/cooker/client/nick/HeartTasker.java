package com.github.cooker.client.nick;

import com.github.cooker.client.RickClient;
import com.github.cooker.client.utils.CommonUtils;
import com.github.cooker.core.msg.RickLogMessageBuilder;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * grant
 * 1/6/2020 3:58 下午
 * 描述：保证空闲时段，网络供应商不清理NAT 网络映射，造成连接中断
 */
@Slf4j
public class HeartTasker implements Consumer<String> {

    RickClient clientApp;

    public HeartTasker(RickClient clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public void accept(String heart) {
        int num = 1;
        while (clientApp.isState()) {
            num ++;
            CommonUtils.sleep(200);
            API.Try(
                    ()-> clientApp.sendMessage(
                        RickLogMessageBuilder.newBuilderWithHeartLog()
                                .create(clientApp.getConf().getString("clientId"))
                                .build()
                )
            ).get();
            CommonUtils.sleep(3000);
            if (num % 5 ==0) {
                //重新注册channel
                API.Try(
                        ()-> clientApp.sendMessage(
                                RickLogMessageBuilder.newBuilderWithHeartLog()
                                        .create0(clientApp.getConf().getString("clientId"))
                                        .build()
                        )
                ).get();
                num = 1;
                CommonUtils.sleep(1000);
            }
        }
        log.info("{} close", heart);
    }
}
