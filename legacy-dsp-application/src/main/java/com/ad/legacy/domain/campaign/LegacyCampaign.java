package com.ad.legacy.domain.campaign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class LegacyCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long userId;
    private Long budget;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public LegacyCampaign(String name, Long userId, Long budget, LocalDateTime createdAt) {
        this.name = name;
        this.userId = userId;
        this.budget = budget;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
    }

    public static LegacyCampaign of(String name, Long userId, Long budget) {
        return new LegacyCampaign(name, userId, budget, LocalDateTime.now());
    }

    public void updateName(String newName) {
        name = newName;
        updatedAt = LocalDateTime.now();
    }

    public void updateBudget(Long newBudget) {
        budget = newBudget;
        updatedAt = LocalDateTime.now();
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }
}
