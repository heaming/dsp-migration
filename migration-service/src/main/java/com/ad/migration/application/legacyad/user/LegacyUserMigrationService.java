package com.ad.migration.application.legacyad.user;

import com.ad.migration.application.LegacyMigrationService;
import com.ad.migration.domain.legacyad.user.LegacyUser;
import com.ad.migration.domain.legacyad.user.LegacyUserRepository;
import com.ad.migration.domain.recentad.user.RecentUser;
import com.ad.migration.domain.recentad.user.RecentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<LegacyUser, RecentUser> {

    public LegacyUserMigrationService(LegacyUserRepository legacyRepository,
                                      LegacyUserConverter converter,
                                      RecentUserRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
