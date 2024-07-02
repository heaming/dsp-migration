package com.ad.legacy.domain.event;

import com.ad.legacy.domain.AggregateType;
import com.ad.legacy.domain.DomainEvent;

import java.time.LocalDateTime;

public record LegacyDomainMessage(AggregateType aggregateType,
                                  Long aggregateId,
                                  Long ownerId,
                                  LocalDateTime occurredOn) {

    public static LegacyDomainMessage from(DomainEvent event) {
        return new LegacyDomainMessage(event.aggregateType(), event.aggregateId(), event.ownerId(), event.occurredOn());
    }
}
