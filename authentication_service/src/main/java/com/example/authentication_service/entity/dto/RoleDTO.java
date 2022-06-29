package com.example.authentication_service.entity.dto;

import com.example.authentication_service.entity.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {
    private String name;
    private Set<String> permission;

    public RoleDTO(Role role) {
        Set<String> list = new HashSet<>();
        this.name = role.getName();
        role.getPermissions().forEach(s->{
             list.add(s.getName());
        });
        this.permission = list;
    }
}


