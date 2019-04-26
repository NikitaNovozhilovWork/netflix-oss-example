package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Employee;
import com.epam.hw.netflix.repositories.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
@RequiredArgsConstructor
public class EmployeeInitializer implements ApplicationRunner {

    private final EmployeesRepository employeesRepository;

    private final List<Employee> employees = newArrayList(
            new Employee("0000001", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000001"),
            new Employee("0000002", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000002"),
            new Employee("0000003", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000003"),
            new Employee("0000004", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000004"),
            new Employee("0000005", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000005"),
            new Employee("0000006", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000006"),
            new Employee("0000007", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000007"),
            new Employee("0000008", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000008"),
            new Employee("0000009", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000009"),
            new Employee("0000010", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000010"),
            new Employee("0000011", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000011"),
            new Employee("0000012", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000012"),
            new Employee("0000013", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000013"),
            new Employee("0000014", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000014"),
            new Employee("0000015", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000015")
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        employees.forEach(employeesRepository::save);
    }

}
