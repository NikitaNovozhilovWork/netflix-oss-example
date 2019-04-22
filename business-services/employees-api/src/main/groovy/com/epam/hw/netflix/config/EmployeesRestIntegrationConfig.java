package com.epam.hw.netflix.config;

import com.epam.hw.netflix.domain.Employee;
import com.epam.hw.netflix.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.lang.String.format;

@Configuration
@Slf4j
public class EmployeesRestIntegrationConfig {

    @Value("${integration.dir.path}")
    private String filesDir;

    //TODO: extract
    @Value("${integration.dir.file.delimiter:;}")
    private String delimiter;

    @Autowired
    private EmployeeService employeeService;

    @Bean
    public ExpressionParser parser() {
        return new SpelExpressionParser();
    }

    @Bean
    public HeaderMapper<HttpHeaders> headerMapper() {
        return new DefaultHttpHeaderMapper();
    }

    @Bean
    public MessagingGatewaySupport refreshGate() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.GET}, "/waffles/refresh"));
        handler.setStatusCodeExpression(parser().parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
        handler.setHeaderMapper(headerMapper());

        return handler;
    }

    @Bean
    public IntegrationFlow refreshFlow() {
        return IntegrationFlows.from(refreshGate())
                .channel("refreshChannel")
                .handle(m -> {
                    File[] files = new File(filesDir).listFiles((File pathname) -> pathname.getName().endsWith(".csv"));
                    if (files != null) {
                        for (File file : files) {
                            try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(file.getAbsolutePath()))) {
                                stream.forEach(line -> {
                                    String[] placeInfo = line.split(delimiter);
                                    employeeService.changeWorkplace(placeInfo[0], placeInfo[1]);
                                });
                            } catch (IOException e) {
                                log.error(format("Error file reading - %s", file.getAbsolutePath()), e);
                            }
                        }
                    }
                })
                .get();
    }

    @Bean
    public MessagingGatewaySupport httpGetGate() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.GET}, "/waffles/{employeesId}"));
        handler.setPayloadExpression(parser().parseExpression("#pathVariables.employeesId"));
        handler.setHeaderMapper(headerMapper());

        return handler;
    }

    @Bean
    public IntegrationFlow httpGetFlow() {
        return IntegrationFlows.from(httpGetGate()).channel("httpGetChannel").handle("employeeEndpoint", "find").get();
    }

    @Bean
    public MessagingGatewaySupport httpPostPutGate() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.PUT, HttpMethod.POST}, "/waffles", "/waffles/{employeesId}"));
        handler.setStatusCodeExpression(parser().parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
        handler.setRequestPayloadTypeClass(Employee.class);
        handler.setHeaderMapper(headerMapper());

        return handler;
    }

    @Bean
    public IntegrationFlow httpPostPutFlow() {
        return IntegrationFlows.from(httpPostPutGate()).channel("routeRequest").route("headers.http_requestMethod",
                m -> m.prefix("http").suffix("Channel")
                        .channelMapping("PUT", "Put")
                        .channelMapping("POST", "Post")
        ).get();
    }

    @Bean
    public IntegrationFlow httpPostFlow() {
        return IntegrationFlows.from("httpPostChannel").handle("employeeEndpoint", "save").get();
    }

    @Bean
    public IntegrationFlow httpPutFlow() {
        return IntegrationFlows.from("httpPutChannel").handle("employeeEndpoint", "update").get();
    }

    private RequestMapping createMapping(HttpMethod[] method, String... path) {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMethods(method);
        requestMapping.setConsumes("application/json");
        requestMapping.setProduces("application/json");
        requestMapping.setPathPatterns(path);

        return requestMapping;
    }

}
