package com.github.cooker.client;

import com.github.cooker.client.handler.RetryConnectHandler;
import com.github.cooker.client.listener.ChannelCloseListener;
import com.github.cooker.core.RickMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;

/**
 * grant
 * 27/5/2020 4:25 下午
 * 描述：
 */
@Slf4j
@EnableAsync
//@EnableRabbit
@SpringBootApplication
public class ClientApp {

    volatile boolean state = true;
    volatile Channel channel;
    PropertiesConfiguration conf;
    EventLoopGroup group;
    Bootstrap bootstrap;

    public boolean isState() {
        return state;
    }

    public void setStateStop() {
        this.state = false;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public PropertiesConfiguration getConf() {
        return conf;
    }

    public boolean sendMessage(RickMessage.msg msg){
        Channel ch = this.channel;
        if (ch != null && ch.isWritable()) {
            log.warn("sendOK message method={} serialNo={} businessNo={}", msg.getMethod(), msg.getSerialNo(), msg.getBusinessNo());
            ch.writeAndFlush(msg);
            return true;
        }else {
            log.warn("sendERR message method={} serialNo={} businessNo={}", msg.getMethod(), msg.getSerialNo(), msg.getBusinessNo());
            return false;
        }
    }

    @PostConstruct
    public void init() throws ConfigurationException {
        log.info("客户端初始化");
        conf = new PropertiesConfiguration(Paths.get("conf.properties").toFile());
        group = new NioEventLoopGroup(conf.getInt("thread.worker"));
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(conf.getString("server.host"), conf.getInt("server.port"))
                .option(ChannelOption.SO_RCVBUF, 128 * 1024)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_SNDBUF, 128 * 1024)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(RickMessage.msg.getDefaultInstance()))
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new RetryConnectHandler(ClientApp.this))
                                .addLast(new ClientHandler());
                    }
                });
    }


    @Bean
    public Bootstrap bootstrap(){
        this.start();
        log.info("客户端启动");
        return bootstrap;
    }

    @Async
    public void start(){
        Channel channel = null;
        try {
            channel = bootstrap.connect()
                    .addListener(new ChannelCloseListener(this))
                    .channel();
            this.setChannel(channel);
        } catch (Exception e) {
            log.error("connect fail >>> {} {}", conf.getString("host"), conf.getInt("port"));
        }
    }

    public static void main(String[] args) throws ConfigurationException {
        SpringApplication.run(ClientApp.class, args).registerShutdownHook();
    }
}
