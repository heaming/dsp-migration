package com.ad.migration.gradual.domain.recentad.campaign;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class RecentCampaign {

    @Id
    private Long id;
    private String name;
    private Long userId;
    private Long budget;
    private String linkUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime migratedAt;
}
