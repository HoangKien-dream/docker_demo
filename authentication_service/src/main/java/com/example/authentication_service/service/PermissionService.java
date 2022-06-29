package com.example.authentication_service.service;

import com.example.authentication_service.entity.Permission;
import com.example.authentication_service.repository.PermissionRepository;
import com.example.authentication_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public Permission save(Permission permission){
        return permissionRepository.save(permission);
    }

    public List<Permission> getAll(){
        return permissionRepository.findAll();
    }

}
