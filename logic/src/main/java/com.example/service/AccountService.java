package com.example.service;

import com.example.entity.Role;
import com.example.repository.AccountRepository;
import com.example.repository.RoleRepository;
import com.example.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;


    @Autowired
    public AccountService(BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository, AccountRepository accountRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        setRolesForAccount(account, 1L);

        return accountRepository.saveAndFlush(account);

//        log.info("IN UserService createUser {}", newUser);
    }

    public void updateAccount(Account account) {

        String password=accountRepository.findById(account.getId()).get().getPassword();

        account.setPassword(password);
        account.setRoleSet(account.getRoleSet());

        accountRepository.saveAndFlush(account);

        log.info("IN UserService updateUser {}", account);
    }

    public void setRolesForAccount(Account account, Long roleId) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(roleId).get());

        account.setRoleSet(roles);
    }

    public void changePassword(Account account, String password) {

        account.setPassword(bCryptPasswordEncoder.encode(password));
        accountRepository.saveAndFlush(account);

        log.info("IN UserService changeUserPassword for user {}", account);

    }

    public Account login(String email, String password) {

        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isPresent()) {
            if (bCryptPasswordEncoder.matches(password, account.get().getPassword())) {
                log.info("IN UserService login account present and password is right");
                return account.get();
            }
        }

        return null;
    }
}
