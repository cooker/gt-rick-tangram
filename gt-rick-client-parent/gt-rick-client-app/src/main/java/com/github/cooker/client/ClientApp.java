package com.github.cooker.client;

import com.github.cooker.client.handler.RetryConnectHandler;
import com.github.cooker.client.listener.ChannelCloseListener;
import com.github.cooker.client.listener.StartedClientListener;
import com.github.cooker.client.listener.StoppedClientListener;
import com.github.cooker.core.RickMessage;
import com.github.cooker.core.utils.MethodContants;
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
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * grant
 * 27/5/2020 4:25 下午
 * 描述：
 */
@Slf4j
@EnableAsync
//@EnableRabbit
@MapperScan("com.github.cooker.client.web.dao")
@SpringBootApplication
public class ClientApp implements RickClient, RickStater{

    volatile boolean state = true;
    volatile Channel channel;
    PropertiesConfiguration conf;
    EventLoopGroup group;
    Bootstrap bootstrap;
    static volatile ClientApp self = null;

    public boolean isState() {
        return state;
    }

    public void setStateStop() {
        this.state = false;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    protected Channel getChannel() {
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
            if (!MethodContants.HEART.equals(msg.getMethod())){
                //打印非心跳消息
                log.info("sendOK message method={} serialNo={} businessNo={}", msg.getMethod(), msg.getSerialNo(), msg.getBusinessNo());
            }
            ch.writeAndFlush(msg);
            return true;
        }else {
            log.error("sendERR message method={} serialNo={} businessNo={}", msg.getMethod(), msg.getSerialNo(), msg.getBusinessNo());
            return false;
        }
    }

    @PostConstruct
    public void init() throws ConfigurationException {
        log.info("客户端初始化");
        self = this;
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

    public static ClientApp getSelf() {
        return self;
    }

    @Bean
    public StartedClientListener startedClientListener(){
        return new StartedClientListener(this);
    }

    @Bean
    public StoppedClientListener stoppedClientListener(){
        return new StoppedClientListener(this);
    }

    @Bean
    public Executor executor(){
        return Executors.newFixedThreadPool(16);
    }
}
