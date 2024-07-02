package com.ad.legacy.api.keyword;

import com.ad.legacy.service.adgroup.LegacyAdGroupResult;
import com.ad.legacy.service.adgroup.LegacyAdGroupService;
import com.ad.legacy.service.keyword.LegacyKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword/v1")
public class LegacyKeywordController {
    private final LegacyKeywordService legacyKeywordService;
    private final LegacyAdGroupService legacyAdGroupService;

    @PostMapping("")
    public LegacyKeywordResponse create(@RequestBody LegacyKeywordCreateRequest request) {
        LegacyAdGroupResult adGroup = legacyAdGroupService.find(request.adGroupId());
        return LegacyKeywordResponse.from(legacyKeywordService.create(request.text(), request.adGroupId(), adGroup.userId()));
    }

    @GetMapping("/{id}")
    public LegacyKeywordResponse find(@PathVariable("id") Long id) {
        return LegacyKeywordResponse.from(legacyKeywordService.find(id));
    }

    @DeleteMapping("/{id}")
    public LegacyKeywordResponse delete(@PathVariable("id") Long id) {
        return LegacyKeywordResponse.from(legacyKeywordService.delete(id));
    }
    
}
