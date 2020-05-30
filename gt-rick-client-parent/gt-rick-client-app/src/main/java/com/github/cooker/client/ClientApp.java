package com.github.cooker.client;

import com.github.cooker.core.RickMessage;
import com.github.cooker.core.RickMethodMessage;
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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Paths;

/**
 * grant
 * 27/5/2020 4:25 下午
 * 描述：
 */
@Slf4j
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

    @PostConstruct
    public void init() throws ConfigurationException {
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
                                .addLast(new ClientHandler());
                    }
                });
    }



    @PreDestroy
    public void stop() throws ConfigurationException {
        PropertiesConfiguration conf = new PropertiesConfiguration(Paths.get("conf.properties").toFile());

        EventLoopGroup group = new NioEventLoopGroup(conf.getInt("thread.worker"));
        Bootstrap bootstrap = new Bootstrap();
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
                                .addLast(new ClientHandler());
                    }
                });
        try {
            Channel channel = bootstrap.connect().sync().channel();
            ChannelFuture future = channel.closeFuture().sync();
            future.addListener(ChannelFutureListener.CLOSE);
        } catch (InterruptedException e) {

        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws ConfigurationException {
        SpringApplication.run(ClientApp.class, args).registerShutdownHook();
    }
}
