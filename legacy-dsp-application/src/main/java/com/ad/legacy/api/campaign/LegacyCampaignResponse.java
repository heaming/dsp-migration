package com.ad.legacy.api.campaign;

import com.ad.legacy.service.campaign.LegacyCampaignResult;

import java.time.LocalDateTime;

public record LegacyCampaignResponse(Long id,
                                     String name,
                                     Long userId,
                                     Long budget,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt,
                                     LocalDateTime deletedAt) {
    public static LegacyCampaignResponse from(LegacyCampaignResult campaign) {
        return new LegacyCampaignResponse(campaign.id(), campaign.name(), campaign.userId(),
                campaign.budget(), campaign.createdAt(), campaign.updatedAt(),
                campaign.deletedAt());
    }
}
