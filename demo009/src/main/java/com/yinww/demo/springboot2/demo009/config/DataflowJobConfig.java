package com.yinww.demo.springboot2.demo009.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.yinww.demo.springboot2.demo009.domain.Mail;
import com.yinww.demo.springboot2.demo009.job.MailSendJob;

@Configuration
public class DataflowJobConfig {

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public DataflowJob<Mail> mailSendJob(){
      return new MailSendJob();
    }
    
    /**
     * 普通邮件定时配置
     * @param mailSendJob
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    @Bean(initMethod = "init")
    public JobScheduler mailSendJobScheduler(final DataflowJob<Mail> mailSendJob, @Value("${mailSendJob.cron}") final String cron,
        @Value("${shardingCategory.shardingTotalCount}") final int shardingTotalCount,
        @Value("${shardingCategory.shardingItemParameters}") final String shardingItemParameters){
        LiteJobConfiguration jobConfiguration = createJobConfiguration(mailSendJob.getClass(), cron, shardingTotalCount, shardingItemParameters);
      return new JobScheduler(regCenter, jobConfiguration);
    }
    
    @SuppressWarnings("rawtypes")
    private LiteJobConfiguration createJobConfiguration(Class<? extends DataflowJob> jobClass, String cron, int shardingTotalCount
            , String shardingItemParameters) {
        // 定义作业核心配置
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build();
        // 定义Dataflow类型配置
        // false 非流式处理数据: 只会在每次作业执行过程中执行一次fetchData方法和processData方法，随即完成本次作业
        DataflowJobConfiguration jobConfiguration = new DataflowJobConfiguration(coreConfig, jobClass.getCanonicalName(), false);
        // 定义Lite作业根配置
        LiteJobConfiguration rootConfig = LiteJobConfiguration.newBuilder(jobConfiguration).overwrite(true).build();
        return rootConfig;
    }

}
