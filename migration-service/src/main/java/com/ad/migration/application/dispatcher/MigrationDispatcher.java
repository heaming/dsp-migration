package com.ad.migration.application.dispatcher;

import com.ad.migration.application.MigrationService;
import com.ad.migration.application.legacyad.adgroup.LegacyAdGroupMigrationService;
import com.ad.migration.application.legacyad.campaign.LegacyCampaignMigrationService;
import com.ad.migration.application.legacyad.keyword.LegacyKeywordMigrationService;
import com.ad.migration.application.legacyad.user.LegacyUserMigrationService;
import com.ad.migration.application.user.MigrationUserService;
import com.ad.migration.domain.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationDispatcher {

    private final MigrationUserService migrationUserService;
    private final LegacyUserMigrationService legacyUserMigrationService;
    private final LegacyCampaignMigrationService legacyCampaignMigrationService;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;
    private final LegacyKeywordMigrationService legacyKeywordMigrationService;

    public boolean dispatch(Long userId, Long aggregateId, AggregateType aggregateType) {
        if(migrationUserService.isDisAgreed(userId)) {
            return false;
        }

        return migrate(userId, aggregateId, aggregateType);
    }

    private boolean migrate(Long userId, Long aggregateId, AggregateType aggregateType) {
        MigrationService service = switch (aggregateType) {
            case USER -> legacyUserMigrationService;
            case CAMPAIGN -> legacyCampaignMigrationService;
            case ADGROUP -> legacyAdGroupMigrationService;
            case KEYWORD -> legacyKeywordMigrationService;
        };
        boolean result = service.migrate(aggregateId);
        logMigrationResult(userId, aggregateId, aggregateType, result);

        return result;
    }

    private void logMigrationResult(Long userId, Long aggregateId, AggregateType aggregateType, boolean result) {
        if(result) {
            log.info("{}", LegacyMigrationLog.success(userId, aggregateType, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(userId, aggregateType, aggregateId));
        }
    }
}
