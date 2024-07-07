package com.ad.migration.gradual.applicaion.legacyad.keyword;

import com.ad.migration.gradual.applicaion.LegacyConverter;
import com.ad.migration.gradual.domain.legacyad.keyword.LegacyKeyword;
import com.ad.migration.gradual.domain.recentad.keyword.RecentKeyword;
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
