package com.ad.migration.internal.api.user;

import com.ad.migration.application.user.MigrationUserResult;
import com.ad.migration.application.user.MigrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1/user")
public class MigrationUserController {
    private final MigrationUserService migrationUserService;

    @PostMapping("/{userId}/agree")
    public MigrationUserResponse agree(@PathVariable Long userId) {
        MigrationUserResult result = migrationUserService.agree(userId);
        return new MigrationUserResponse(result.id(), result.status(), result.agreedAt(), result.updatedAt());
    }

    @GetMapping("/{userId}")
    public MigrationUserResponse find(@PathVariable Long userId) {
        MigrationUserResult result = migrationUserService.findById(userId);
        return new MigrationUserResponse(result.id(), result.status(), result.agreedAt(), result.updatedAt());
    }
}
