package com.ad.migration.application.dispatcher;

import com.ad.migration.domain.AggregateType;

public record LegacyMigrationLog(boolean isSuccess, Long userId, AggregateType aggregateType, Long aggregateId) {

    public static LegacyMigrationLog success(Long userId, AggregateType aggregateType, Long aggregateId) {
        return new LegacyMigrationLog(true, userId, aggregateType, aggregateId);
    }
    public static LegacyMigrationLog fail(Long userId, AggregateType aggregateType, Long aggregateId) {
        return new LegacyMigrationLog(false, userId, aggregateType, aggregateId);
    }

}
