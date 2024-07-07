package com.ad.migration.application.legacyad.keyword;

import com.ad.migration.application.LegacyMigrationService;
import com.ad.migration.domain.legacyad.keyword.LegacyKeyword;
import com.ad.migration.domain.legacyad.keyword.LegacyKeywordRepository;
import com.ad.migration.domain.recentad.keyword.RecentKeyword;
import com.ad.migration.domain.recentad.keyword.RecentKeywordRepository;
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
