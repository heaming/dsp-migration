package com.ad.legacy.service.campaign;

import com.ad.legacy.domain.campaign.LegacyCampaign;
import com.ad.legacy.domain.campaign.LegacyCampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LegacyCampaignService {
    private final LegacyCampaignRepository legacyCampaignRepository;

    @Transactional
    public LegacyCampaignResult create(String name, Long userId, Long budget) {
        LegacyCampaign campaign = LegacyCampaign.of(name, userId, budget);
        return save(campaign);
    }

    private LegacyCampaignResult save(LegacyCampaign campaign) {
        return LegacyCampaignResult.from(legacyCampaignRepository.save(campaign));
    }

    public LegacyCampaignResult find(Long id) {
        return LegacyCampaignResult.from(findById(id));
    }

    private LegacyCampaign findById(Long id) {
        return legacyCampaignRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public LegacyCampaignResult updateName(Long id, String name) {
        LegacyCampaign campaign = findById(id);
        campaign.updateName(name);
        return save(campaign);
    }

    @Transactional
    public LegacyCampaignResult updateBudget(Long id, Long budget) {
        LegacyCampaign campaign = findById(id);
        campaign.updateBudget(budget);
        return save(campaign);
    }

    @Transactional
    public LegacyCampaignResult delete(Long id) {
        LegacyCampaign campaign = findById(id);
        campaign.delete();
        return save(campaign);
    }
}
