package com.ad.migration.application.legacyad.adgroup;

import com.ad.migration.application.LegacyMigrationService;
import com.ad.migration.domain.legacyad.adgroup.LegacyAdGroup;
import com.ad.migration.domain.legacyad.adgroup.LegacyAdGroupRepository;
import com.ad.migration.domain.recentad.campaign.RecentCampaign;
import com.ad.migration.domain.recentad.campaign.RecentCampaignRepository;
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
