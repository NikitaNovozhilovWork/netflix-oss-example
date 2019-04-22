package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.exceptions.NoWorkspaceFoundException;
import com.epam.hw.netflix.repositories.WorkspacesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class WorkplaceService {

    private final WorkspacesRepository workspacesRepository;

    public Workspace findWorkspace(String id) {
        return workspacesRepository
                .findById(id)
                .orElseThrow(() -> new NoWorkspaceFoundException(format("No workspace found with id: %s", id)));
    }
}
