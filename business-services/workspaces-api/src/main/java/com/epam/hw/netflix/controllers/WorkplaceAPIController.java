package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.WorkspaceAPI;
import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.services.WorkplaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/workspaces")
@Slf4j
@RefreshScope
public class WorkplaceAPIController implements WorkspaceAPI {

    @Autowired
    private WorkplaceService workplaceService;

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/{id}")
    public Workspace getWorkspaceById(@PathVariable("id") String id) {
        log.info("Instance {} received get workspace request; refreshable message - {}", this, message);
        return workplaceService.findWorkspace(id);
    }

    @PostMapping
    public void addWorkspace(@RequestBody Workspace workspace) {
        log.info("Instance {} received post workspace request; refreshable message - {}", this, message);
        workplaceService.addWorkspace(workspace);
    }

    @PutMapping("/{id}")
    public void saveWorkspace(@PathVariable("id") String id, @RequestBody Workspace workspace) {
        log.info("Instance {} received put workspace request; refreshable message - {}", this, message);
        workspace.setId(id);
        workplaceService.addWorkspace(workspace);
    }
}
