package com.ad.migration.internal.api.migration;

import com.ad.migration.application.dispatcher.MigrationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1")
public class MigrationController {

    private final MigrationDispatcher dispatcher;

    @PutMapping("/retry")
    public MigrationRetryResponse retry(@RequestBody MigrationRetryRequest request) {
        boolean result = dispatcher.dispatch(request.userId(), request.aggregateId(), request.aggregateType());
        return new MigrationRetryResponse(result);
    }
}
