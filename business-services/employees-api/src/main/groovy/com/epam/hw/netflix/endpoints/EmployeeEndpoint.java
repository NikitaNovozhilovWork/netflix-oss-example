package com.epam.hw.netflix.endpoints;

import com.epam.hw.netflix.config.IntegrationConfig;
import com.epam.hw.netflix.domain.Employee;
import com.epam.hw.netflix.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeEndpoint {

    private static final String STATUSCODE_HEADER = "http_statusCode";

    private final EmployeeService employeeService;
    private final IntegrationConfig integrationConfig;

    public void save(Message<Employee> employeeMessage) {
        employeeService.addEmployee(employeeMessage.getPayload());
    }

    public void update(Message<Employee> employeeMessage) {
        employeeService.addEmployee(employeeMessage.getPayload());
    }

    public void changePlace(Message<String> placeCsv) {
        log.info(placeCsv.getPayload());
        String[] placeInfo = placeCsv.getPayload().split(integrationConfig.getFileDelimiter());
        employeeService.changeWorkplace(placeInfo[0], placeInfo[1]);
    }

    public Message<?> find(Message<String> msg) {
        Employee employee = employeeService.findEmployee(msg.getPayload());

        if (employee == null) {
            return MessageBuilder.fromMessage(msg)
                    .copyHeadersIfAbsent(msg.getHeaders())
                    .setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
                    .build();
        }

        return MessageBuilder.withPayload(employee)
                .copyHeadersIfAbsent(msg.getHeaders())
                .setHeader(STATUSCODE_HEADER, HttpStatus.OK)
                .build();
    }

}
