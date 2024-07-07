package com.ad.legacy.domain.keyword.event;

import com.ad.legacy.domain.AggregateType;
import com.ad.legacy.domain.DomainEvent;
import com.ad.legacy.domain.keyword.LegacyKeyword;

import java.time.LocalDateTime;

public abstract class LegacyKeywordEvent implements DomainEvent {

    protected LegacyKeyword legacyKeyword;

    public LegacyKeywordEvent(LegacyKeyword legacyKeyword) {
        this.legacyKeyword = legacyKeyword;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }

    @Override
    public Long aggregateId() {
        return legacyKeyword.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

    @Override
    public Long ownerId() {
        return legacyKeyword.getUserId();
    }
}
