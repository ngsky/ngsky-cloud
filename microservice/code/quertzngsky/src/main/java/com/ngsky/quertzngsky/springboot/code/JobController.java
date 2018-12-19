package com.ngsky.quertzngsky.springboot.code;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * <dl>
 * <dt>JobController</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/13/2018 11:17 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@RestController
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private DynamicJobService jobService;

    // 初始化启动所有的job
    @PostConstruct
    public void initialize() {
        try {
            logger.info("restart all jobs start...");
            reStartAllJobs();
            logger.info("INIT SUCCESS");
        } catch (SchedulerException e) {
            logger.info("INIT Exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    // 重启启动所有的 job
    private void reStartAllJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
        for (JobKey jobKey : set) {
            scheduler.deleteJob(jobKey);
        }
        for (JobEntity job : jobService.loadJobs()) {
            logger.info("Job register name: {}, group: {}, cron: {},", job.getName(), job.getGroup(), job.getCron());
            JobDataMap map = jobService.getJobDataMap(job);
            JobKey jobKey = jobService.getJobKey(job);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
            if (job.getStatus().equals("OPEN"))
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
            else
                logger.info("Job jump name: {}, because {} status is {} ", job.getName(), job.getName(), job.getStatus());
        }
    }

    // 根据ID重启某个Job
    @RequestMapping(value = "/refresh/{id}")
    public String refresh(@PathVariable Integer id) {
        String ret;
        JobEntity entity = jobService.getJobEntityById(id);
        if (entity == null) return "error: id is not exists";
        TriggerKey triggerKey = new TriggerKey(entity.getName(), entity.getGroup());
        JobKey jobKey = jobService.getJobKey(entity);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(entity);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, entity.getDescription(), map);
            if (entity.getStatus().equals("OPEN")) {
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(entity));
                ret = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " success !";
            } else {
                ret = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " failed !"
                        + "Because the Job status is " + entity.getStatus();
            }
        } catch (SchedulerException e) {
            ret = "Error while Refresh " + e.getMessage();
        }
        return ret;
    }

    // 重启数据库中所有Job
    @RequestMapping("/refresh/all")
    public String refreshAll() {
        String ret;
        try {
            reStartAllJobs();
            ret = "SUCCESS";
        } catch (SchedulerException e) {
            ret = "EXCEPTION : " + e.getMessage();
        }
        return ret;
    }
}
