package com.ad.legacy.api.adgroup;

import com.ad.legacy.api.campaign.LegacyAdGroupCreateRequest;
import com.ad.legacy.service.adgroup.LegacyAdGroupService;
import com.ad.legacy.service.campaign.LegacyCampaignResult;
import com.ad.legacy.service.campaign.LegacyCampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adgroup/v1")
public class LegacyAdgroupController {
    private final LegacyAdGroupService legacyAdGroupService;
    private final LegacyCampaignService legacyCampaignService;


    @PostMapping("")
    public LegacyAdGroupResponse create(@RequestBody LegacyAdGroupCreateRequest request) {
        LegacyCampaignResult campaign = legacyCampaignService.find(request.campaignId());
        return LegacyAdGroupResponse.from(legacyAdGroupService.create(request.name(), campaign.userId(), campaign.id(), request.linkUrl()));
    }

    @GetMapping("/{id}")
    public LegacyAdGroupResponse find(@PathVariable("id") Long id) {
        return LegacyAdGroupResponse.from(legacyAdGroupService.find(id));
    }

    @PatchMapping("/name")
    public LegacyAdGroupResponse updateName(@RequestBody LegacyAdGroupUpdateNameRequest request) {
        return LegacyAdGroupResponse.from(legacyAdGroupService.updateName(request.id(), request.name()));
    }

    @PatchMapping("/link-url")
    public LegacyAdGroupResponse updateLinkUrl(@RequestBody LegacyAdGroupUpdateLinkUrlRequest request) {
        return LegacyAdGroupResponse.from(legacyAdGroupService.updateLinkUrl(request.id(), request.linkUrl()));
    }

    @DeleteMapping("/{id}")
    public LegacyAdGroupResponse delete(@PathVariable("id") Long id) {
        return LegacyAdGroupResponse.from(legacyAdGroupService.delete(id));
    }
}
