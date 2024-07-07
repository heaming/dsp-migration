package com.ad.migration.gradual.applicaion.legacyad.user;

import com.ad.migration.gradual.applicaion.LegacyMigrationService;
import com.ad.migration.gradual.domain.legacyad.user.LegacyUser;
import com.ad.migration.gradual.domain.legacyad.user.LegacyUserRepository;
import com.ad.migration.gradual.domain.recentad.user.RecentUser;
import com.ad.migration.gradual.domain.recentad.user.RecentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<LegacyUser, RecentUser> {

    public LegacyUserMigrationService(LegacyUserRepository legacyRepository,
                                      LegacyUserConverter converter,
                                      RecentUserRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
