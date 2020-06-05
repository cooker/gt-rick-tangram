package com.github.cooker.server;

import com.github.cooker.server.dq.ChannelManager;

import com.github.cooker.server.dq.CommonUtils;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ZoomGrant 2020/5/31 17:20
 */
@Slf4j
@Component
public class BaseChannelManager implements ChannelManager {
    ListMultimap<String, Channel> channels = LinkedListMultimap.create();
    Map<ChannelId, String> clientIds = new ConcurrentHashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();
    @Override
    public boolean addChannel(String clientId, Channel channel) {
        //防止批量重复操作
        if (channels.containsKey(clientId)) return true;
        log.debug("clientId={} channel 新增 {}", clientId, channel);
        try {
            lock.writeLock().lock();
            List<Channel> chs = channels.get(clientId);
            if (chs == null) {
                chs = Lists.newLinkedList();
                chs.add(channel);
            }
            clientIds.put(channel.id(), clientId);
            return channels.put(clientId, channel);
        }catch (Exception e){
            log.error("clientId={} channel 新增失败 >> {}", clientId, channel);
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void removeChannel(String clientId, Channel channel) {
        log.debug("clientId={} channel 移除 {}", clientId, channel);
        try {
            lock.writeLock().lock();
            List<Channel> chs = channels.get(clientId);
            if(chs != null) chs.remove(channel);
            if (clientIds.containsKey(channel.id())) clientIds.remove(channel.id());
        }catch (Exception e){
            log.error("clientId={} channel 移除失败 >> {}", clientId, CommonUtils.getClientHost(channel));
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void clearChannel(String clientId) {
        log.debug("clientId={} channel 清空", clientId);
        try {
            lock.writeLock().lock();
            List<Channel> chs = channels.get(clientId);
            if(chs != null) {
                for (Channel ch : chs) {
                    clientIds.remove(ch.id());
                }
                chs.clear();
            };
        }catch (Exception e){
            log.error("clientId={} channel 清理失败", clientId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean removeChannel(Channel channel) {
        try {
            lock.writeLock().lock();
            String clientId = clientIds.get(channel.id());
            log.debug("clientId={} channel >> {} 清理", clientId, channel);
            if(clientId != null) {
                List<Channel> chs = channels.get(clientId);
                clientIds.remove(channel.id());
                return chs.remove(channel);
            }
            return true;
        }catch (Exception e){
            log.error("channel ip={} channel 清理失败", channel);
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Channel getChannel(String clientId) {
        List<Channel> chs = channels.get(clientId);
        if (chs == null || chs.isEmpty()) {
            return null;
        }else {
            return chs.get(0);
        }
    }
}
