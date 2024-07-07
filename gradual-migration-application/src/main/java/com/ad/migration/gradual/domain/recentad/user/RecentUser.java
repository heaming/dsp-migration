package com.ad.migration.gradual.domain.recentad.user;

import com.ad.migration.gradual.domain.recentad.MigratedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class RecentUser implements MigratedEntity {

    @Id
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime migratedAt;

    private RecentUser(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime migratedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.migratedAt = migratedAt;
    }

    public static RecentUser migrated(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        return new RecentUser(id, name, createdAt, updatedAt, deletedAt, LocalDateTime.now());
    }
}
