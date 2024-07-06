package com.ad.migration.gradual.applicaion.legacyad.adgroup;

import com.ad.migration.gradual.applicaion.LegacyConverter;
import com.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroup;
import com.ad.migration.gradual.domain.legacyad.campaign.LegacyCampaign;
import com.ad.migration.gradual.domain.legacyad.campaign.LegacyCampaignRepository;
import com.ad.migration.gradual.domain.recentad.campaign.RecentCampaign;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LegacyAdGroupConverter implements LegacyConverter<LegacyAdGroup, RecentCampaign> {

    private final LegacyCampaignRepository legacyCampaignRepository;

    @Override
    public RecentCampaign convert(LegacyAdGroup legacyAdGroup) {
        LegacyCampaign campaign = legacyCampaignRepository.findById(legacyAdGroup.getId())
                .orElseThrow(EntityNotFoundException::new);

        return RecentCampaign.migrated(
                legacyAdGroup.getId(),
                campaign.getName()+"_"+legacyAdGroup.getName(),
                legacyAdGroup.getUserId(),
                campaign.getBudget(),
                legacyAdGroup.getLinkUrl(),
                legacyAdGroup.getCreatedAt(),
                legacyAdGroup.getUpdatedAt(),
                legacyAdGroup.getDeletedAt()
        );
    }
}
