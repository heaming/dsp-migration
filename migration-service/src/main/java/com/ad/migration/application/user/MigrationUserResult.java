package com.ad.migration.application.user;

import com.ad.migration.domain.migration.user.MigrationUser;
import com.ad.migration.domain.migration.user.MigrationUserStatus;

import java.time.LocalDateTime;

public record MigrationUserResult(Long id, MigrationUserStatus status, LocalDateTime agreedAt, LocalDateTime updatedAt) {

    public static MigrationUserResult from(MigrationUser user) {
        return new MigrationUserResult(user.getId(), user.getStatus(), user.getAgreedAt(), user.getUpdatedAt());
    }
}
