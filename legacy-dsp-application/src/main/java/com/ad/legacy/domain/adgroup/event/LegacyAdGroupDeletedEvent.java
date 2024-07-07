package com.ad.legacy.domain.adgroup.event;

import com.ad.legacy.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public class LegacyAdGroupDeletedEvent extends LegacyAdGroupEvent {

    public LegacyAdGroupDeletedEvent(LegacyAdGroup legacyAdGroup) {
        super(legacyAdGroup);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyAdGroup.getDeletedAt();
    }
}
