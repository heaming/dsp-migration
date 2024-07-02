package com.ad.legacy.service.keyword;

import com.ad.legacy.domain.keyword.LegacyKeyword;
import com.ad.legacy.domain.keyword.LegacyKeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LegacyKeywordService {
    private final LegacyKeywordRepository legacyKeywordRepository;

    @Transactional
    public LegacyKeywordResult create(String text, Long adgroupId, Long userId) {
        LegacyKeyword keyword = LegacyKeyword.of(text, adgroupId, userId);
        return save(keyword);
    }

    private LegacyKeywordResult save(LegacyKeyword keyword) {
        return LegacyKeywordResult.from(legacyKeywordRepository.save(keyword));
    }

    public LegacyKeywordResult find(Long id) {
        return LegacyKeywordResult.from(findById(id));
    }

    private LegacyKeyword findById(Long id) {
        return legacyKeywordRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public LegacyKeywordResult delete(Long id) {
        LegacyKeyword keyword = findById(id);
        keyword.delete();
        return save(keyword);
    }
}
