package com.github.cooker.server;

import com.github.cooker.server.common.BlackIpFilterRule;
import com.github.cooker.server.listener.StopServerListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * grant
 * 26/5/2020 2:27 下午
 * 描述：
 */
@Slf4j
@EnableAsync
@SpringBootApplication
//@EnableRabbit
//@EnableKafka
public class ServerApp {
    static NioEventLoopGroup workerGroup = null;
    static NioEventLoopGroup bossGroup = null;
    static ServerBootstrap bootstrap = null;
    static Configuration conf = null;
    volatile static ConfigurableApplicationContext context=null;

    @PostConstruct
    public void init(){
        try {
            conf = new PropertiesConfiguration(Paths.get("conf.properties").toFile());
            Iterator<String> keys = conf.getKeys();
            log.info("配置信息加载");
            while (keys.hasNext()){
                String key = keys.next();
                log.info("{}={}", key, conf.getString(key));
            }
            workerGroup = new NioEventLoopGroup(conf.getInt("thread.worker", 16));
            bossGroup = new NioEventLoopGroup(conf.getInt("thread.boss", 8));
            bootstrap = new ServerBootstrap().group(bossGroup, workerGroup);
        } catch (ConfigurationException e) {
            log.error("配置文件读取失败...", e);
        }
    }

    @Bean
    public ServerBootstrap serverBootstrap(BlackIpFilterRule ipFilterRule){
        log.info("server 初始化");
        bootstrap.channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(8 * 1024 * 1024, 16 * 1024 * 1024))
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_RCVBUF, 128 * 1024)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.SO_SNDBUF, 128 * 1024)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ServerHandlerInitializer(this ,ipFilterRule));
        log.info("server start");
        this.start();
        return bootstrap;
    }

    @Bean
    public ApplicationListener serverCloseListener(){
        return new StopServerListener();
    }

    @Async
    public void start(){
        ChannelFuture future = null;
        try {
            future = bootstrap.bind(conf.getInt("port",7113)).sync();
        }catch (Exception e){
            log.error("服务出现异常", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }finally {
            if (future != null){
                future.channel().closeFuture();
            }
        }
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    public static NioEventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public static NioEventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public static ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    public static Configuration getConf() {
        return conf;
    }

    public static void main(String[] args) {

        context = SpringApplication.run(ServerApp.class, args);
        context.registerShutdownHook();
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
