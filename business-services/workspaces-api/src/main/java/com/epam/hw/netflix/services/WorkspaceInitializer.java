package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.repositories.WorkspacesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.hw.netflix.domain.OSFamily.*;
import static com.epam.hw.netflix.domain.OSFamily.LINUX;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
public class WorkspaceInitializer implements ApplicationRunner {

    private final WorkspacesRepository workspacesRepository;

    private final List<Workspace> workspaces = newArrayList(
            new Workspace("0000001", 1, 1, randomUUID().toString(), WINDOWS),
            new Workspace("0000002", 1, 2, randomUUID().toString(), WINDOWS),
            new Workspace("0000003", 1, 3, randomUUID().toString(), WINDOWS),
            new Workspace("0000004", 1, 4, randomUUID().toString(), OSX),
            new Workspace("0000005", 1, 5, randomUUID().toString(), OSX),
            new Workspace("0000006", 1, 6, randomUUID().toString(), OSX),
            new Workspace("0000007", 1, 7, randomUUID().toString(), WINDOWS),
            new Workspace("0000008", 2, 1, randomUUID().toString(), WINDOWS),
            new Workspace("0000009", 2, 2, randomUUID().toString(), WINDOWS),
            new Workspace("0000010", 2, 3, randomUUID().toString(), OSX),
            new Workspace("0000011", 2, 4, randomUUID().toString(), OSX),
            new Workspace("0000012", 2, 5, randomUUID().toString(), WINDOWS),
            new Workspace("0000013", 2, 6, randomUUID().toString(), WINDOWS),
            new Workspace("0000014", 2, 7, randomUUID().toString(), LINUX),
            new Workspace("0000015", 2, 9, randomUUID().toString(), LINUX)
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        workspaces.forEach(workspacesRepository::save);
    }

}
