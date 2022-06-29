package com.example.authentication_service.entity.dto;
import com.example.authentication_service.entity.Account;
import com.example.authentication_service.entity.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String username;
    private Set<Role> roles;
    private String thumbnail;
    private int gender;
    private LocalDate birthday;
    private String phone;
    private String address;
    private String email;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.roles = account.getRoles();
        this.thumbnail = account.getThumbnail();
        this.gender = account.getGender();
        this.birthday = account.getBirthday();
        this.address = account.getAddress();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.status = account.getStatus();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }

}
