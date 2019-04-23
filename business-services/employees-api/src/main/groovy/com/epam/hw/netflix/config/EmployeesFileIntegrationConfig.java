package com.epam.hw.netflix.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

import java.io.File;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty("integration.dir.path")
public class EmployeesFileIntegrationConfig {

    private final IntegrationConfig integrationConfig;

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(Files.inboundAdapter(new File(integrationConfig.getPath()))
                                .autoCreateDirectory(true)
                                .filter(new SimplePatternFileListFilter("*.csv"))
                                .useWatchService(true)
                                .watchEvents(FileReadingMessageSource.WatchEventType.CREATE,
                                             FileReadingMessageSource.WatchEventType.MODIFY),
                        e -> e.poller(Pollers.fixedDelay(integrationConfig.getRefresh())
                                .maxMessagesPerPoll(integrationConfig.getMaxMessages())))
                .split(Files.splitter())
                .handle("employeeEndpoint", "changePlace")
                .get();
    }

}
