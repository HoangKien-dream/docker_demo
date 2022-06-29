package com.example.authentication_service.controller;

import com.example.authenservice.entity.Permission;
import com.example.authenservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @RequestMapping(method = RequestMethod.POST)
    public Permission save(@RequestBody Permission permission){
        return permissionService.save(permission);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Permission> getAll (){
        return permissionService.getAll();
    }
}
