package com.epam.hw.netflix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "integration.dir")
public class IntegrationConfig {

    private String path;
    private long refresh;
    private int maxMessages;
    private String fileDelimiter;

}
