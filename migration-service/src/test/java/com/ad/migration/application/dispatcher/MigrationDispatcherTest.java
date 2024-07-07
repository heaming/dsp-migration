package com.ad.migration.application.dispatcher;

import com.ad.migration.application.legacyad.adgroup.LegacyAdGroupMigrationService;
import com.ad.migration.application.legacyad.campaign.LegacyCampaignMigrationService;
import com.ad.migration.application.legacyad.keyword.LegacyKeywordMigrationService;
import com.ad.migration.application.legacyad.user.LegacyUserMigrationService;
import com.ad.migration.application.user.MigrationUserService;
import com.ad.migration.domain.AggregateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MigrationDispatcherTest {

    @Mock
    MigrationUserService migrationUserService;

    @Mock
    LegacyUserMigrationService legacyUserMigrationService;

    @Mock
    LegacyCampaignMigrationService legacyCampaignMigrationService;

    @Mock
    LegacyAdGroupMigrationService legacyAdGroupMigrationService;

    @Mock
    LegacyKeywordMigrationService legacyKeywordMigrationService;

    @InjectMocks
    MigrationDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("사용자가 마이그레이션에 동의하지 않았으면 dispatch -> false")
    void disagreed(boolean migrationSuccess) {
        when(migrationUserService.isDisAgreed(1L)).thenReturn(true);
        when(legacyAdGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("사용자가 마이그레이션에 동의 dispatch -> true")
    void agreed(boolean migrationSuccess) {
        when(migrationUserService.isDisAgreed(1L)).thenReturn(false);
        when(legacyAdGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isEqualTo(migrationSuccess);

    }


}