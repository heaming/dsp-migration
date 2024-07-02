package com.ad.legacy.api.user;

import com.ad.legacy.service.user.LegacyUserResult;

import java.time.LocalDateTime;

public record LegacyUserResponse(Long id,
                                 String name,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 LocalDateTime deletedAt) {

    public static LegacyUserResponse from(LegacyUserResult result) {
        return new LegacyUserResponse(result.id(), result.name(), result.createdAt(), result.updatedAt(), result.deletedAt());
    }

}
