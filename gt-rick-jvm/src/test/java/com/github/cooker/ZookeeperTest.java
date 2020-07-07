package com.github.cooker;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * ZoomGrant 2020/6/21 8:04
 */
public class ZookeeperTest {

    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 20000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.toString());
            }
        });
    }

    @Test
    public void get() throws IOException, KeeperException, InterruptedException {
        zooKeeper.getData("/xa",false,new Stat());
    }


}
