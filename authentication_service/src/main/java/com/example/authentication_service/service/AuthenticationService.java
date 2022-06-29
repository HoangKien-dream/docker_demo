package com.example.authentication_service.service;
import com.example.authentication_service.entity.Account;
import com.example.authentication_service.entity.Permission;
import com.example.authentication_service.entity.Role;
import com.example.authentication_service.entity.dto.AccountDTO;
import com.example.authentication_service.entity.dto.RegisterDTO;
import com.example.authentication_service.repository.AccountRepository;
import com.example.authentication_service.repository.RoleRepository;
import com.example.authentication_service.util.DateTimeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final String USER_ROLE = "User";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        Account account = accountOptional.orElse(null);
        if (account == null) {
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role :
                account.getRoles()) {
            for (Permission permission:
                 role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
//            authorities.add(new SimpleGrantedAuthority(new Gson().toJson(new RoleDTO(role))));
        }
        UserDetails userDetail
                = new User(account.getUsername(), account.getPassword(), authorities);
        return
                userDetail;
    }

    public AccountDTO addRole(String username, String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account != null) {
            account.getRoles().add(role);
            return new AccountDTO(account);
        }
        return null;
    }

    public AccountDTO removeRole(String username, String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account != null) {
            account.getRoles().remove(role);
            return new AccountDTO(account);
        }
        return null;
    }

    public List<AccountDTO> getAll() {
        List<AccountDTO> dtoList = new ArrayList<>();
        for (Account account :
                accountRepository.findAll()) {
            dtoList.add(new AccountDTO(account));
        }
        return dtoList;
    }

    public AccountDTO updated(int id, int status) {
        Account oldAccount = accountRepository.findById(id).orElse(null);
        if (oldAccount != null) {
            oldAccount.setStatus(status);
            return new AccountDTO(oldAccount);
        }
        return null;
    }

    public AccountDTO saveAccount(RegisterDTO registerDTO) {
        //create new user role if not exist
        Set<Role> roleSet = new HashSet<>();
        Optional<Role> userRoleOptional = roleRepository.findByName(USER_ROLE);
        Role userRole = userRoleOptional.orElse(null);
        if (userRole == null) {
            //create new role
            userRole.setName(USER_ROLE);
        }
        roleSet.add(userRole);
        //check if username has exist
        Optional<Account> byUsername = accountRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            return null;
        }
        Set<Account> accountSet = new HashSet<>();
        Account account = new Account();
        account.setUsername(registerDTO.getUsername());
        account.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        account.setCreatedAt(new Date());
        account.setUpdatedAt(new Date());
        account.setAddress(registerDTO.getAddress());
        LocalDate birthday = DateTimeHelper.convertStringToLocalDate(registerDTO.getBirthday());
        account.setBirthday(birthday);
        account.setGender(registerDTO.getGender());
        account.setPhone(registerDTO.getPhone());
        account.setThumbnail(registerDTO.getThumbnail());
        account.setEmail(registerDTO.getEmail());
        account.setRoles(roleSet);
        account.setStatus(1);
        accountSet.add(account);
        userRole.setAccounts(accountSet);
        Account save = accountRepository.save(account);
        return new AccountDTO(save);
    }

    public Account getAccount(String username) {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        return byUsername.orElse(null);
    }
}
