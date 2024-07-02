package com.ad.legacy.domain.user.event;

import com.ad.legacy.domain.AggregateType;
import com.ad.legacy.domain.DomainEvent;
import com.ad.legacy.domain.user.LegacyUser;

import java.time.LocalDateTime;

public abstract class LegacyUserEvent implements DomainEvent {

    protected LegacyUser legacyUser;

    public LegacyUserEvent(LegacyUser legacyUser) {
        this.legacyUser = legacyUser;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.USER;
    }

    @Override
    public Long aggregateId() {
        return legacyUser.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

    @Override
    public Long ownerId() {
        return legacyUser.getId();
    }
}
