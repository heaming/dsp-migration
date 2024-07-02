package com.ad.legacy.domain.adgroup;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LegacyAdGroupTest {

    LegacyAdGroup adGroup = LegacyAdGroup.of("ad-group", 1L, 1L, "https://www.naver.com");

    @Test
    void updateLinkUrl() {
        LocalDateTime before = LocalDateTime.now();
        adGroup.updateLinkUrl("https://www.fastcampus.co.kr");
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(adGroup.getUpdatedAt())
                        .isAfterOrEqualTo(before)
                        .isBeforeOrEqualTo(after),
                () -> assertThat(adGroup.getLinkUrl())
                        .isEqualTo("https://www.fastcampus.co.kr")
        );
    }
}