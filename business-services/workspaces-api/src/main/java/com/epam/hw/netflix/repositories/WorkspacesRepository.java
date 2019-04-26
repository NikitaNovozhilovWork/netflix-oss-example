package com.epam.hw.netflix.repositories;

import com.epam.hw.netflix.domain.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspacesRepository extends CrudRepository<Workspace, String> {
}
