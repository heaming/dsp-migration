package com.ad.migration.application.user;

import com.ad.migration.domain.migration.user.MigrationUser;
import com.ad.migration.domain.migration.user.MigrationUserRepository;
import com.ad.migration.domain.migration.user.MigrationUserStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MigrationUserServiceTest {

    @Mock
    MigrationUserRepository repository;

    @InjectMocks
    MigrationUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("동의 성공")
    void agree() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        LocalDateTime before = LocalDateTime.now();
        MigrationUserResult result = service.agree(1L);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(result.id()).isEqualTo(1L),
                () -> assertThat(result.status()).isEqualTo(MigrationUserStatus.AGREED),
                () -> assertThat(result.updatedAt())
                        .isAfterOrEqualTo(before)
                        .isBeforeOrEqualTo(after),
                () -> assertThat(result.agreedAt())
                        .isAfterOrEqualTo(before)
                        .isBeforeOrEqualTo(after)
        );
    }

    @Test
    @DisplayName("이미 동의된 경우 error")
    void alreadyAgree() {
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        assertThatThrownBy(() -> service.agree(1L))
                .isInstanceOf(AlreadyAgreedException.class);
    }

    @Test
    @DisplayName("조회시 없는 경우")
    void notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("동의하지 않은 경우 -> true")
    void disAgreed() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.isDisAgreed(1L);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("동의했다면 -> false")
    void notDisAgreed() {
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        boolean result = service.isDisAgreed(1L);

        assertThat(result).isFalse();
    }

}