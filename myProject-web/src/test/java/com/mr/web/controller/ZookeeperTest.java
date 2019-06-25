package com.mr.web.controller;

import com.mr.web.zookeeper.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ZookeeperTest
 * @Description ZookeeperTest
 * @Author stack
 * @Version 1.0
 * @since 2019/6/25 10:57
 */
public class ZookeeperTest extends BaseTest {

    @Test
    public void testCuratorFrTest1() throws Exception{
        String lockName = "/aaa";
        String root="/root";
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new BoundedExponentialBackoffRetry(3, 1000, 2000));
        ExecutorService executorService = Executors.newCachedThreadPool();
        curatorFramework.start();
        InterProcessMutex  interProcessMutex = new InterProcessMutex(curatorFramework, root.concat(lockName));
        int count = 0;
        while (!interProcessMutex.acquire(1, TimeUnit.SECONDS)) {
            count++;
            if (count > 3) {
                break;
            }
        }

        interProcessMutex.release();
        curatorFramework.delete().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {

            }
        }, executorService).forPath("/root".concat(lockName));

    }

    public static void main(String[] args) throws Exception {
        DistributedLock distributedLock = new DistributedLock("/aaa");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"启动");
                while (!distributedLock.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"尝试获取锁");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    System.out.println(Thread.currentThread().getName()+"获得了锁,睡眠");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"释放锁");
                distributedLock.releaseLock();
            }).start();
        }

    }


}
