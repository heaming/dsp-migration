package com.ad.migration.gradual.message;

import com.ad.migration.application.dispatcher.MigrationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class LegacyDomainMessageHandler {

    private final MigrationDispatcher migrationDispatcher;

    @Bean
    public Consumer<LegacyDomainMessage> legacyConsumer() {
        return message -> migrationDispatcher.dispatch(message.aggregateId(), message.aggregateType());
    }
}

