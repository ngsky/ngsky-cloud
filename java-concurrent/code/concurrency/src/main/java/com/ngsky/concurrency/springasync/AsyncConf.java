package com.ngsky.concurrency.springasync;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <dl>
 * <dt>AsyncConf</dt>
 * <dd>Description:
 * 开启异步执行
 * </dd>
 * <dd>CreateDate: 12/18/2018 11:46 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.ngsky.concurrency.springasync")
public class AsyncConf {
}
