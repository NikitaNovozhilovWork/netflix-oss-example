package com.epam.hw.netflix.repositories;

import com.epam.hw.netflix.domain.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeesRepository extends CrudRepository<Employee, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.workspaceId = :workspaceId WHERE e.id = :employeeId")
    int updateWorkspace(@Param("employeeId") String employeeId, @Param("workspaceId") String workspaceId);

}
