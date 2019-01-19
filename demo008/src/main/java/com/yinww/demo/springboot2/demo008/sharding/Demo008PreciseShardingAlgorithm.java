package com.yinww.demo.springboot2.demo008.sharding;

import java.util.Collection;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

public class Demo008PreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String postfix = "" + (shardingValue.getValue() / 2) % 10;
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(postfix)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }

}
