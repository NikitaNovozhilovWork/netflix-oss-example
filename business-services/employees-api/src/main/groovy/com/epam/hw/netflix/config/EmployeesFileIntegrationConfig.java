package com.epam.hw.netflix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.dsl.Files;

import java.io.File;

@Configuration
public class EmployeesFileIntegrationConfig {

    @Value("${integration.dir.path}")
    private String filesDir;

    @Value("${integration.dir.refresh}")
    private long refresh;

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(Files.inboundAdapter(new File(filesDir))
                                .autoCreateDirectory(true)
                                .patternFilter("*.csv")
                                .useWatchService(true)
                                .watchEvents(FileReadingMessageSource.WatchEventType.CREATE,
                                        FileReadingMessageSource.WatchEventType.MODIFY),
                        e -> e.poller(Pollers.fixedDelay(refresh)))
                .split(Files.splitter())
                .handle("employeeEndpoint", "changePlace")
                .get();
    }

}
