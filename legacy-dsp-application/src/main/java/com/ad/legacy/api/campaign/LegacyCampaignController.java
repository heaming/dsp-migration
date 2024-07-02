package com.ad.legacy.api.campaign;

import com.ad.legacy.service.campaign.LegacyCampaignService;
import com.ad.legacy.service.user.LegacyUserResult;
import com.ad.legacy.service.user.LegacyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campaign/v1")
public class LegacyCampaignController {
    private final LegacyCampaignService legacyCampaignService;
    private final LegacyUserService legacyUserService;

    @PostMapping("")
    public LegacyCampaignResponse create(@RequestBody LegacyCampaignCreateRequest request) {
        LegacyUserResult user = legacyUserService.find(request.userId());
        return LegacyCampaignResponse.from(legacyCampaignService.create(request.name(), user.id(), request.budget()));
    }

    @GetMapping("/{id}")
    public LegacyCampaignResponse find(@PathVariable("id") Long id) {
        return LegacyCampaignResponse.from(legacyCampaignService.find(id));
    }

    @PatchMapping("/name")
    public LegacyCampaignResponse updateName(@RequestBody LegacyCampaignUpdateNameRequest request) {
        return LegacyCampaignResponse.from(legacyCampaignService.updateName(request.id(), request.name()));
    }

    @PatchMapping("/budget")
    public LegacyCampaignResponse updateBudget(@RequestBody LegacyCampaignUpdateBudgetRequest request) {
        return LegacyCampaignResponse.from(legacyCampaignService.updateBudget(request.id(), request.budget()));
    }

    @DeleteMapping("/{id}")
    public LegacyCampaignResponse delete(@PathVariable("id") Long id) {
        return LegacyCampaignResponse.from(legacyCampaignService.delete(id));
    }

}
