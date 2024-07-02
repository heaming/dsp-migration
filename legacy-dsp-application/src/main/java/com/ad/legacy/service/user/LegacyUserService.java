package com.ad.legacy.service.user;

import com.ad.legacy.domain.user.LegacyUser;
import com.ad.legacy.domain.user.LegacyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LegacyUserService {
    private final LegacyUserRepository legacyUserRepository;

    @Transactional
    public LegacyUserResult create(String name) {
        LegacyUser user = LegacyUser.of(name);
        return save(user);
    }

    private LegacyUserResult save(LegacyUser user) {
        return LegacyUserResult.from(legacyUserRepository.save(user));
    }

    public LegacyUserResult find(Long id) {
        return LegacyUserResult.from(findById(id));
    }

    private LegacyUser findById(Long id) {
        return legacyUserRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public LegacyUserResult updateName(Long id, String name) {
        LegacyUser user = findById(id);
        user.updateName(name);
        return save(user);
    }
    @Transactional
    public LegacyUserResult delete(Long id) {
        LegacyUser user = findById(id);
        user.delete();
        return save(user);
    }
}
