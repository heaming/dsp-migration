package com.ad.legacy.domain.event;

import com.ad.legacy.domain.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class LegacyDomainEventListener {

    @TransactionalEventListener
    public void handleEvent(DomainEvent event) {

    }
}
