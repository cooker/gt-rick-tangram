package com.github.cooker.server;

import com.github.cooker.core.RickMessage;
import com.github.cooker.server.dq.BlackIpFilterRule;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * grant
 * 26/5/2020 3:11 下午
 * 描述：
 */
@Slf4j
public class ServerHandlerInitializer extends ChannelInitializer {

    IpFilterRule ipFilterRule;

    public ServerHandlerInitializer(IpFilterRule ipFilterRule) {
        this.ipFilterRule = ipFilterRule;
    }


    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                //11 秒没有向客户端发送消息就发生心跳
//          .addLast(new IdleStateHandler(11, 0, 0))
                // google Protobuf 编解码
//          .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(new RuleBasedIpFilter(ipFilterRule))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(RickMessage.msg.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new ServerHandler());
    }
}
