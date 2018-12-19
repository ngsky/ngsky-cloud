package com.ngsky.quartzngsky.code;

import com.ngsky.quartzngsky.repository.JobEntityRepository;
import com.ngsky.quartzngsky.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sunyx@lenovocloud.com
 * @CreateDate: 2018/12/18 12:17
 */
@Service
public class JobTaskService {

    private final static Logger logger = LoggerFactory.getLogger(JobTaskService.class);

    @Autowired
    private JobEntityRepository jobEntityRepository;

    // 添加task
    public boolean addTask(JobEntity jobEntity) {
        try {
            jobEntity.setCtime(TimeUtil.dateFormat(new Date()));
            jobEntityRepository.save(jobEntity);
        } catch (Exception e) {
            logger.info("add task throw exception: {}", e.getMessage());
            return false;
        }
        return true;
    }

    // 获取所有的 task
    public List<JobEntity> getAllTasks() {
        List<JobEntity> list = new ArrayList<>();
        jobEntityRepository.findAll().forEach(list::add);
        return list;
    }

    // 根据taskId 查询task
    public JobEntity getTaskById(Long taskId) {
        return jobEntityRepository.getById(taskId);
    }


    // 更改job.cron
    public void updateJobCron(Long jobId, String cron) {
        JobEntity job = jobEntityRepository.getById(jobId);
        if (StringUtils.isEmpty(job)) {
            logger.info("update job cron failed, because cannot find the job by jobId:{}", jobId);
            return;
        }

    }

    // 更改job.status
    public void updateJobStatus(Long jobId, String status) {
    }

    // 立即执行job
    public void runJobNow(JobEntity job) {
    }

    // 删除一个job
    public boolean removeJob(Long jobId) {
    }

    // 重新开始一个job
    public boolean resumeJob(Long jobId) {
    }

    // 暂停一个job
    public boolean pauseJob(Long jobId) {
    }

    // 所有正在运行的job
    public List<JobEntity> getAllActiveJobs() {
    }

    // 获取所有计划中的任务列表
    public List<JobEntity> getAllPlanJobs() {
    }

}
