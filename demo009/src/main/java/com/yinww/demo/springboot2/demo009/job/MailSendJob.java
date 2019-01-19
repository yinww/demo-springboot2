package com.yinww.demo.springboot2.demo009.job;

import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.yinww.demo.springboot2.demo009.domain.Mail;

public class MailSendJob implements DataflowJob<Mail> {

    @Override
    public List<Mail> fetchData(ShardingContext shardingContext) {
        System.out.println(shardingContext.getShardingItem() + "====" + shardingContext.getShardingTotalCount());
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Mail> data) {
        // TODO Auto-generated method stub
    }

}
