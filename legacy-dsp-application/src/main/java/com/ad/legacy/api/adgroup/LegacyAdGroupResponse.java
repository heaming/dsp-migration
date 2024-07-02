package com.ad.legacy.api.adgroup;

import com.ad.legacy.service.adgroup.LegacyAdGroupResult;

import java.time.LocalDateTime;

public record LegacyAdGroupResponse (Long id,
                                     String name,
                                     Long campaignId,
                                     String linkUrl,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt,
                                     LocalDateTime deletedAt) {

    public static LegacyAdGroupResponse from(LegacyAdGroupResult adGroup) {
        return new LegacyAdGroupResponse(adGroup.id(), adGroup.name(), adGroup.campaignId(),
                adGroup.linkUrl(), adGroup.createdAt(), adGroup.updatedAt(),
                adGroup.deletedAt());
    }
}