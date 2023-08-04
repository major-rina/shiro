package com.poluhin.shiro.example.controller;

import com.poluhin.shiro.example.domain.exception.NotFoundException;
import com.poluhin.shiro.example.domain.model.Resource;
import com.poluhin.shiro.example.domain.dao.ResourcesDao;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/resources",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {

    private final ResourcesDao resourcesDao;

    @GetMapping
    @RequiresPermissions("resources:read")
    public Collection<Resource> resourceList() {
        return resourcesDao.resourceList();
    }

    @GetMapping( "/{id}")
    @RequiresPermissions("resources:read")
    public Resource getResource(@PathVariable("id") String id) throws NotFoundException {

        Resource resource = resourcesDao.getResource(id);
        if (resource == null) {
            throw new NotFoundException(id);
        }
        return resource;
    }

    @PostMapping
    @RequiresPermissions("resources:create")
    public Resource createResource(@RequestBody Resource resource) {

        return resourcesDao.addResource(resource);
    }

    @PostMapping("/{id}")
    @RequiresPermissions("resources:update")
    public Resource updateResource(@PathVariable("id") String id, @RequestBody Resource updatedTrooper) throws NotFoundException {

        return resourcesDao.updateResource(id, updatedTrooper);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequiresPermissions("resources:delete")
    public void deleteResource(@PathVariable("id") String id) {
        resourcesDao.deleteResource(id);
    }


}
