package com.ngsky.quertzngsky.springboot.code;

import com.ngsky.quertzngsky.springboot.utils.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>DynamicJob</dt>
 * <dd>Description:
 *
 * @author daxiong
 * @DisallowConcurrentExecution:此标记用在实现Job的类上面，意思是不运行并发执行 注意：org.quartz.threadPool.threadCount 线程池中线程的数量至少要多个，否则@DisallowConcurrentExecution不生效
 * 假如Job的设置时间间隔为3s，但Job执行时间是5s，设置@DisallowConcurrentExecution以后，程序会等任务执行完毕后再去执行，否则在3s时再启动新的线程执行
 * </dd>
 * <dd>CreateDate: 12/13/2018 10:30 PM</dd>
 * </dl>
 */
@DisallowConcurrentExecution
@Component
public class DynamicJob implements Job {

    private Logger logger = LoggerFactory.getLogger(DynamicJob.class);

    /**
     * 核心方法：Quartz Job 真正的执行逻辑
     *
     * @param jobExecutionContext 封装了 Quartz 运行所需要的所有信息
     * @throws JobExecutionException execute() 执行时允许抛出的唯一异常类型
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // JobDetail 中的 JobDataMap 是共用的，从getMergedJobDataMap获取的JobDataMap是全新的对象
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String jarPath = map.getString("jarPath");
        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        logger.info("Running Job name: {} ", map.getString("name"));
        logger.info("Running Job description: {} ", map.getString("description"));
        logger.info("Running Job cron: {} ", map.getString("cronExpression"));
        logger.info("Running Job jar path: {} ", jarPath);
        logger.info("Running Job parameter: {} ", parameter);
        logger.info("Running Job vmParam: {} ", vmParam);

        long startTime = System.currentTimeMillis();
        if (!StringUtils.getStringUtil.isEmpty(jarPath)) {
            File jar = new File(jarPath);
            if (jar.exists()) {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(jar.getParentFile());
                List<String> commands = new ArrayList<>();
                commands.add("java");
                if (!StringUtils.getStringUtil.isEmpty(vmParam)) {
                    commands.add(vmParam);
                }
                commands.add("-jar");
                commands.add(jarPath);
                if (!StringUtils.getStringUtil.isEmpty(parameter)) commands.add(parameter);
                processBuilder.command(commands);
                logger.info("Running Job details as follows >>>>>>>>>>>>>: ");
                logger.info("Running Job commands: {}", StringUtils.getStringUtil.getListString(commands));

                try {
                    Process process = processBuilder.start();
                    logProcess(process.getInputStream(), process.getErrorStream());
                } catch (IOException exception) {
                    throw new JobExecutionException(exception);
                }
            } else
                throw new JobExecutionException("Job Jar not found >> " + jarPath);
        }

        long endTime = System.currentTimeMillis();
        logger.info(">>>>>>>>>>> Running Job has been completed, cost time : {} ", (endTime - startTime) + "ms \n");
    }

    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {
        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        while ((inputLine = inputReader.readLine()) != null) logger.info(inputLine);
        while ((errorLine = errorReader.readLine()) != null) logger.info(errorLine);
    }
}
