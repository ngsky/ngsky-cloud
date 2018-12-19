package com.ngsky.quertzngsky.springboot.code;

import com.ngsky.quertzngsky.springboot.repository.JobEntityRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>DynamicJobService</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/13/2018 11:03 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Service
public class DynamicJobService {
    @Autowired
    private JobEntityRepository repository;

    public JobEntity getJobEntityById(Integer id) {
        return repository.getById(id);
    }

    public List<JobEntity> loadJobs() {
        List<JobEntity> jobList = new ArrayList<>();
        repository.findAll().forEach(jobList::add);
        return jobList;
    }

    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("group", job.getGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    // 获取JobDetail, JobDetail 是任务的定义，而Job是任务的执行逻辑，JobDetail里会引用一个Job Class 类定义
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    // 获取 Trigger (Job的触发器，执行规则)
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    // 获取 JobKey , 包含 Name 和 Group
    public JobKey getJobKey(JobEntity job){
        return JobKey.jobKey(job.getName(), job.getGroup());
    }
}
