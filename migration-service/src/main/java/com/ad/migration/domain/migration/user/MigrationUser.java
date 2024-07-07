package com.ad.migration.domain.migration.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class MigrationUser extends AbstractAggregateRoot<MigrationUser> {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private MigrationUserStatus status;

    private LocalDateTime agreedAt;
    private LocalDateTime updatedAt;

    public MigrationUser(Long id, LocalDateTime agreedAt) {
        this.id = id;
        this.status = MigrationUserStatus.AGREED;
        this.agreedAt = agreedAt;
        this.updatedAt = agreedAt;
        registerEvent(new MigrationAgreedEvent(this));
    }

    public static MigrationUser agreed(Long id) {
        return new MigrationUser(id, LocalDateTime.now());
    }
}
