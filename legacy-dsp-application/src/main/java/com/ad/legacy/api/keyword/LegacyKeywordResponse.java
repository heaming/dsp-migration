package com.ad.legacy.api.keyword;

import com.ad.legacy.service.keyword.LegacyKeywordResult;

import java.time.LocalDateTime;

public record LegacyKeywordResponse(Long id,
                                    String text,
                                    Long adGroupId,
                                    LocalDateTime createdAt,
                                    LocalDateTime deletedAt) {
    public static LegacyKeywordResponse from(LegacyKeywordResult keyword) {
        return new LegacyKeywordResponse(keyword.id(), keyword.text(), keyword.adGroupId(),
                keyword.createdAt(), keyword.deletedAt());
    }
}
