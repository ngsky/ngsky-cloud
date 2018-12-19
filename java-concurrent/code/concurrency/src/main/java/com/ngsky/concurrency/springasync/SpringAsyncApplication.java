package com.ngsky.concurrency.springasync;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <dl>
 * <dt>SpringAsyncApplication</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/18/2018 11:52 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class SpringAsyncApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AsyncConf.class);
        Syncdb syncdb = acac.getBean(Syncdb.class);
        syncdb.syncUser();
        syncdb.syncTeam();
    }
}
