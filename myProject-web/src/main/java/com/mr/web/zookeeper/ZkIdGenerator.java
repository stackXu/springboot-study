package com.mr.web.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 使用zk生成分布式唯一id，自增有序
 *
 * @author CC11001100
 */
public class ZkIdGenerator {
 
    private ZooKeeper zk;
    private String path;
 
    public ZkIdGenerator(String serverAddress, String path) {
        try {
            this.path = path;
            zk = new ZooKeeper(serverAddress, 3000, event -> {
                System.out.println(event.toString());
            });
 
            if (zk.exists(path, false) == null) {
                zk.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
 
    public long next() {
        try {
            Stat stat = zk.setData(path, new byte[0], -1);
            return stat.getVersion();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
 
}