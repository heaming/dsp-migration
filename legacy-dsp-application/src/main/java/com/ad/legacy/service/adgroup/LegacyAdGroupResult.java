package com.ad.legacy.service.adgroup;

import com.ad.legacy.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public record LegacyAdGroupResult(Long id,
                                  String name,
                                  Long userId,
                                  Long campaignId,
                                  String linkUrl,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt,
                                  LocalDateTime deletedAt) {

    public static LegacyAdGroupResult from(LegacyAdGroup adGroup) {
        return new LegacyAdGroupResult(adGroup.getId(), adGroup.getName(), adGroup.getCampaignId(),
                adGroup.getUserId(), adGroup.getLinkUrl(), adGroup.getCreatedAt(),
                adGroup.getUpdatedAt(), adGroup.getDeletedAt());
    }
}
