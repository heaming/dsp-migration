package com.ad.migration.gradual.applicaion.legacyad.user;

import com.ad.migration.gradual.applicaion.LegacyConverter;
import com.ad.migration.gradual.domain.legacyad.user.LegacyUser;
import com.ad.migration.gradual.domain.recentad.user.RecentUser;
import org.springframework.stereotype.Component;

@Component
public class LegacyUserConverter implements LegacyConverter<LegacyUser, RecentUser> {
    @Override
    public RecentUser convert(LegacyUser legacyUser) {
        return RecentUser.migrated(
                legacyUser.getId(),
                legacyUser.getName(),
                legacyUser.getCreatedAt(),
                legacyUser.getUpdatedAt(),
                legacyUser.getDeletedAt()
                );
    }
}
