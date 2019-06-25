package com.mr.web.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类描述：分布式唯一ID生成器
 * <p>
 * 创建人：ternence
 */
@Component
public class DistributedIdGeneraterService {

    private static CuratorFramework curatorFrameworkClient;

    private static RetryPolicy retryPolicy;

    private static ExecutorService executorService;

    private static String IP_TOSTRING = "localhost:2181";

    private static String ROOT = "/root";

    private static String NODE_NAME = "idgenerator";

    static {
        // 0 000 000 004
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
        curatorFrameworkClient = CuratorFrameworkFactory.builder().connectString(IP_TOSTRING).sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        curatorFrameworkClient.start();
        try {
            executorService = Executors.newFixedThreadPool(10);
            //请先判断父节点/root节点是否存在
            Stat stat = curatorFrameworkClient.checkExists().forPath(ROOT);
            if (stat == null) {
                curatorFrameworkClient.create().withMode(CreateMode.PERSISTENT).forPath(ROOT, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateId() {
        String backPath = "";
        String ID = "";
        String fullPath = ROOT.concat("/").concat(NODE_NAME);
        try {
            // 关键点：创建持久顺序节点
            backPath = curatorFrameworkClient.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(fullPath, null);
            //为防止生成的节点浪费系统资源，故生成后异步删除此节点
            String finalBackPath = backPath;
            executorService.execute(() -> {
                try {
                    curatorFrameworkClient.delete().forPath(finalBackPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ID = splitID(backPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ID;
    }

    public String splitID(String path) {
        int index = path.lastIndexOf(NODE_NAME);
        if (index >= 0) {
            index += NODE_NAME.length();
            return index <= path.length() ? path.substring(index) : "";
        }
        return path;
    }
}
