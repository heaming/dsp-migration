package com.ad.migration.internal.api.user;

import com.ad.migration.domain.migration.user.MigrationUserStatus;

import java.time.LocalDateTime;

public record MigrationUserResponse(Long id, MigrationUserStatus status,
                                    LocalDateTime agreedAt, LocalDateTime updatedAt) {
}
