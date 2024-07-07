package com.ad.migration.gradual.applicaion.legacyad.campaign;

import com.ad.migration.gradual.applicaion.LegacyMigrationService;
import com.ad.migration.gradual.applicaion.legacyad.adgroup.LegacyAdGroupMigrationService;
import com.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroup;
import com.ad.migration.gradual.domain.legacyad.adgroup.LegacyAdGroupRepository;
import com.ad.migration.gradual.domain.legacyad.campaign.LegacyCampaign;
import com.ad.migration.gradual.domain.recentad.campaign.RecentCampaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyCampaignMigrationService extends LegacyMigrationService<LegacyCampaign, RecentCampaign> {

    private final LegacyAdGroupRepository legacyAdGroupRepository;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;

    public LegacyCampaignMigrationService(CrudRepository<LegacyCampaign, Long> legacyRepository,
                                          CrudRepository<RecentCampaign, Long> recentRepository,
                                          LegacyAdGroupRepository legacyAdGroupRepository,
                                          LegacyAdGroupMigrationService legacyAdGroupMigrationService) {
        super(legacyRepository, null, recentRepository);
        this.legacyAdGroupRepository = legacyAdGroupRepository;
        this.legacyAdGroupMigrationService = legacyAdGroupMigrationService;
    }

    @Override
    protected void migrate(LegacyCampaign legacyCampaign) {
        for(LegacyAdGroup legacyAdGroup : legacyAdGroupRepository.findAllByCampaignIdAndDeletedAtIsNull(legacyCampaign.getId())) {
            legacyAdGroupMigrationService.migrate(legacyAdGroup.getId());
        }
    }
}
