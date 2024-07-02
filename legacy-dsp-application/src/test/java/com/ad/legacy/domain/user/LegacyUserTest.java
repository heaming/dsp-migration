package com.ad.legacy.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LegacyUserTest {

    LegacyUser user = LegacyUser.of("name");

    @Test
    void updateName() {
        LocalDateTime before = LocalDateTime.now();
        user.updateName("new name");
        LocalDateTime after = LocalDateTime.now();

        assertAll(
            () -> assertThat(user.getName()).isEqualTo("new name"),
            () -> assertThat(user.getUpdatedAt())
                .isAfterOrEqualTo(before)
                .isBeforeOrEqualTo(after));
    }

    @Test
    void delete() {
        LocalDateTime before = LocalDateTime.now();
        user.delete();
        LocalDateTime after = LocalDateTime.now();

        assertThat(user.getDeletedAt())
                .isAfterOrEqualTo(before)
                .isBeforeOrEqualTo(after);
    }
}