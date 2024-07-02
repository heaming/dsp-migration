package com.ad.legacy.api.user;

import com.ad.legacy.service.user.LegacyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class LegacyUserController {
    private final LegacyUserService legacyUserService;

    @PostMapping("")
    public LegacyUserResponse create(@RequestBody LegacyUserCreateRequest request) {
        return LegacyUserResponse.from(legacyUserService.create(request.name()));
    }

    @GetMapping("/{id}")
    public LegacyUserResponse find(@PathVariable("id") Long id) {
        return LegacyUserResponse.from(legacyUserService.find(id));
    }

    @PatchMapping("/name")
    public LegacyUserResponse updateName(@RequestBody LegacyUserUpdateNameRequest request) {
        return LegacyUserResponse.from(legacyUserService.updateName(request.id(), request.name()));
    }

    @DeleteMapping("/{id}")
    public LegacyUserResponse delete(@PathVariable("id") Long id) {
        return LegacyUserResponse.from(legacyUserService.delete(id));
    }
}
