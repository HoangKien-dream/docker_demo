package com.example.authentication_service.controller;

import com.example.authenservice.entity.Role;
import com.example.authenservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @RequestMapping(method = RequestMethod.POST)
    public Role save(@RequestBody Role role){
        return roleService.save(role);
    }
    @RequestMapping(path = "updated",method = RequestMethod.PUT)
    public Role addPermissonToRole(@RequestParam int id, @RequestParam int roleId){
        return roleService.addPermissionToRole(id,roleId);
    }
    @RequestMapping(path = "delete",method = RequestMethod.DELETE)
    public Role removePermission(@RequestParam int id, @RequestParam int roleId){
        return roleService.removePermission(id,roleId);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getAll(){
        return roleService.getAll();
    }
}
