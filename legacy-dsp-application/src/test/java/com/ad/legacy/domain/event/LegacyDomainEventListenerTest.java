package com.ad.legacy.domain.event;

import com.ad.legacy.domain.DomainEvent;
import com.ad.legacy.domain.adgroup.event.*;
import com.ad.legacy.domain.campaign.event.*;
import com.ad.legacy.domain.keyword.event.LegacyKeywordCreatedEvent;
import com.ad.legacy.domain.keyword.event.LegacyKeywordDeletedEvent;
import com.ad.legacy.domain.keyword.event.LegacyKeywordEvent;
import com.ad.legacy.domain.user.event.LegacyUserCreatedEvent;
import com.ad.legacy.domain.user.event.LegacyUserDeletedEvent;
import com.ad.legacy.domain.user.event.LegacyUserEvent;
import com.ad.legacy.domain.user.event.LegacyUserNameUpdatedEvent;
import com.ad.legacy.service.adgroup.LegacyAdGroupResult;
import com.ad.legacy.service.adgroup.LegacyAdGroupService;
import com.ad.legacy.service.campaign.LegacyCampaignResult;
import com.ad.legacy.service.campaign.LegacyCampaignService;
import com.ad.legacy.service.keyword.LegacyKeywordResult;
import com.ad.legacy.service.keyword.LegacyKeywordService;
import com.ad.legacy.service.user.LegacyUserResult;
import com.ad.legacy.service.user.LegacyUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class LegacyDomainEventListenerTest {

    @Autowired
    LegacyUserService userService;
    @Autowired
    LegacyCampaignService campaignService;
    @Autowired
    LegacyAdGroupService adGroupService;
    @Autowired
    LegacyKeywordService keywordService;

    @MockBean
    LegacyDomainEventListener eventListener;

    @Test
    void userEvents() {
        LegacyUserResult result = userService.create("사용자");
        userService.updateName(result.id(), "n_사용자");
        userService.delete(result.id());

        assertAll(
                () -> verify(eventListener, times(3)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(3)).handleEvent(any(LegacyUserEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserDeletedEvent.class))
        );
    }

    @Test
    void campaignEvents() {
        LegacyCampaignResult result = campaignService.create("캠페인", 1L, 100L);
        campaignService.updateBudget(result.id(), 200L);
        campaignService.updateName(result.id(), "n_cam");
        campaignService.delete(result.id());

        assertAll(
                () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(4)).handleEvent(any(LegacyCampaignEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignBudgetUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignDeletedEvent.class))
        );
    }

    @Test
    void adGroupEvents() {
        LegacyAdGroupResult result = adGroupService.create("adgroup1", 1L, 1L, "url1");
        adGroupService.updateName(result.id(), "adgroup1-2");
        adGroupService.updateLinkUrl(result.id(), "n_url");
        adGroupService.delete(result.id());

        assertAll(
                () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(4)).handleEvent(any(LegacyAdGroupEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupLinkUrlUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupDeletedEvent.class))
        );
    }
    @Test
    void keywordEvents() {
        LegacyKeywordResult result = keywordService.create("keyword", 1L, 1L);
        keywordService.delete(result.id());
        assertAll(
                () -> verify(eventListener, times(2)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(2)).handleEvent(any(LegacyKeywordEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordDeletedEvent.class))
        );
    }
}