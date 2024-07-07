package com.ad.migration.application.dispatcher;

import com.ad.migration.application.MigrationService;
import com.ad.migration.application.legacyad.adgroup.LegacyAdGroupMigrationService;
import com.ad.migration.application.legacyad.campaign.LegacyCampaignMigrationService;
import com.ad.migration.application.legacyad.keyword.LegacyKeywordMigrationService;
import com.ad.migration.application.legacyad.user.LegacyUserMigrationService;
import com.ad.migration.domain.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationDispatcher {

    private final LegacyUserMigrationService legacyUserMigrationService;
    private final LegacyCampaignMigrationService legacyCampaignMigrationService;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;
    private final LegacyKeywordMigrationService legacyKeywordMigrationService;

    public boolean dispatch(Long aggregateId, AggregateType aggregateType) {
        return migrate(aggregateId, aggregateType);
    }

    private boolean migrate(Long aggregateId, AggregateType aggregateType) {
        MigrationService service = switch (aggregateType) {
            case USER -> legacyUserMigrationService;
            case CAMPAIGN -> legacyCampaignMigrationService;
            case ADGROUP -> legacyAdGroupMigrationService;
            case KEYWORD -> legacyKeywordMigrationService;
        };
        boolean result = service.migrate(aggregateId);
        logMigrationResult(aggregateId, aggregateType, result);

        return result;
    }

    private void logMigrationResult(Long aggregateId, AggregateType aggregateType, boolean result) {
        if(result) {
            log.info("{}", LegacyMigrationLog.success(aggregateType, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(aggregateType, aggregateId));
        }
    }
}
