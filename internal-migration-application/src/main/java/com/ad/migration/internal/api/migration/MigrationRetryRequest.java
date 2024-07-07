package com.ad.migration.internal.api.migration;

import com.ad.migration.domain.AggregateType;

public record MigrationRetryRequest(Long userId, Long aggregateId, AggregateType aggregateType) {
}
