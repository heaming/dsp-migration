package com.ad.migration.application.legacyad.keyword;

import com.ad.migration.application.LegacyConverter;
import com.ad.migration.domain.legacyad.keyword.LegacyKeyword;
import com.ad.migration.domain.recentad.keyword.RecentKeyword;
import org.springframework.stereotype.Component;

@Component
public class LegacyKeywordConverter implements LegacyConverter<LegacyKeyword, RecentKeyword> {
    @Override
    public RecentKeyword convert(LegacyKeyword legacyKeyword) {
        return RecentKeyword.migrated(
                legacyKeyword.getId(),
                legacyKeyword.getText(),
                legacyKeyword.getAdGroupId(),
                legacyKeyword.getUserId(),
                legacyKeyword.getCreatedAt(),
                legacyKeyword.getDeletedAt());
    }
}
