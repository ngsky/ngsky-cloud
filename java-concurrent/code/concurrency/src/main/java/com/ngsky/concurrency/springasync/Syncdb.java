package com.ngsky.concurrency.springasync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>Syncdb</dt>
 * <dd>Description:
 * 基于 spring 的异步任务
 * </dd>
 * <dd>CreateDate: 12/18/2018 11:50 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Service
public class Syncdb {
    // 同步用户
    @Async
    public void syncUser() {
        while (true) {
            System.out.println("开始同步用户数据...");
        }
    }

    // 同步组织架构数据
    @Async
    public void syncTeam() {
        while (true) {
            System.out.println("开始同步组织架构数据...");
        }
    }
}
