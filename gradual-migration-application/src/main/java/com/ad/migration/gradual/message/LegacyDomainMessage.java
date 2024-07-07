package com.ad.migration.gradual.message;

import com.ad.migration.domain.AggregateType;

import java.time.LocalDateTime;

public record LegacyDomainMessage(AggregateType aggregateType,
                                  Long aggregateId,
                                  Long ownerId,
                                  LocalDateTime occurredOn) {
}
