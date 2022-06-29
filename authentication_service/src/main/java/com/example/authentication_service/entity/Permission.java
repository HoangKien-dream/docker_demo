package com.example.authentication_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions", cascade = {CascadeType.ALL})
    private Set<Role> roles;
}
