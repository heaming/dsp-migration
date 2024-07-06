package com.ad.migration.gradual.applicaion.legacyad.adgroup;

import com.ad.migration.gradual.applicaion.LegacyMigrationService;
import com.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroup;
import com.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroupRepository;
import com.ad.migration.gradual.domain.recentad.campaign.RecentCampaign;
import com.ad.migration.gradual.domain.recentad.campaign.RecentCampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyAdGroupMigrationService extends LegacyMigrationService<LegacyAdGroup, RecentCampaign> {

    public LegacyAdGroupMigrationService(
            LegacyAdGroupRepository legacyAdGroupRepository,
            LegacyAdGroupConverter converter,
            RecentCampaignRepository recentCampaignRepository
    ) {
        super(legacyAdGroupRepository, converter, recentCampaignRepository);
    }
}
