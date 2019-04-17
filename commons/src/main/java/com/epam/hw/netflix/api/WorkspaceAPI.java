package com.epam.hw.netflix.api;

import com.epam.hw.netflix.domain.Workspace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "workspaces-api", fallback = WorkspaceAPI.WorkspaceAPIFallback.class)
public interface WorkspaceAPI {

    @RequestMapping(value = "/workspaces/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Workspace getWorkspaceById(@PathVariable("id") String id);

    @Component
    public static class WorkspaceAPIFallback implements WorkspaceAPI {

        @Override
        public Workspace getWorkspaceById(String id) {
            return new Workspace()
                    .setId("0")
                    .setOsFamily(null)
                    .setSeat(0)
                    .setSerialNumber("Fallback for the workspace api")
                    .setUnit(0);
        }

    }
}
