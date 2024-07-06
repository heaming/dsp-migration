package com.ad.migration.gradual.applicaion.legacyad.keyword;

import com.ad.migration.gradual.applicaion.LegacyMigrationService;
import com.ad.migration.gradual.domain.legacyad.keyword.LegacyKeyword;
import com.ad.migration.gradual.domain.legacyad.keyword.LegacyKeywordRepository;
import com.ad.migration.gradual.domain.recentad.keyword.RecentKeyword;
import com.ad.migration.gradual.domain.recentad.keyword.RecentKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyKeywordMigrationService extends LegacyMigrationService<LegacyKeyword, RecentKeyword> {

    public LegacyKeywordMigrationService(
            LegacyKeywordRepository legacyKeywordRepository,
            LegacyKeywordConverter converter,
            RecentKeywordRepository recentKeywordRepository
    ) {
        super(legacyKeywordRepository, converter, recentKeywordRepository);
    }
}
