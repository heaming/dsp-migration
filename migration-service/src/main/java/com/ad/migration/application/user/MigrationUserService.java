package com.ad.migration.application.user;

import com.ad.migration.domain.migration.user.MigrationUser;
import com.ad.migration.domain.migration.user.MigrationUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MigrationUserService {
    private final MigrationUserRepository migrationUserRepository;

    @Transactional
    public MigrationUserResult agree(Long userId) {
        migrationUserRepository.findById(userId).ifPresent(user -> {
            throw new AlreadyAgreedException("User [ID: %d] already agreed".formatted(userId));
        });

        return MigrationUserResult.from(migrationUserRepository.save(MigrationUser.agreed(userId)));
    }

    public MigrationUserResult findById(Long userId) {
        return MigrationUserResult.from(migrationUserRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new));
    }
}
