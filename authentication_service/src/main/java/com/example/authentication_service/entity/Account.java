package com.example.authentication_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username; // select * from account where username = "username"-> salt, passwordhash, passwordHash
    private String password; // đã mã hoá. salt+passwordhash (md5, sha)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "role_account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private String thumbnail;
    private int gender;
    private LocalDate birthday;
    private String phone;
    private String address;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private int status;
    private String verifyCode;
}
