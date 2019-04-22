package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Employee;
import com.epam.hw.netflix.exceptions.NoEmployeeFoundException;
import com.epam.hw.netflix.repositories.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepository employeesRepository;

    public Employee findEmployee(String id) {
        return employeesRepository
                .findById(id)
                .orElseThrow(() -> new NoEmployeeFoundException(format("No employee found for id: %s", id)));
    }

    public int changeWorkplace(String employeeId, String workplaceId) {
        return employeesRepository.updateWorkspace(employeeId, workplaceId);
    }

    public void addEmployee(Employee employee) {
        employeesRepository.save(employee);
    }

}
