package com.onlytours.service;

import com.onlytours.dto.AccountDto;
import com.onlytours.entity.Role;
import com.onlytours.enums.Roles;
import com.onlytours.mapper.AccountMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.RoleRepository;
import com.onlytours.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleStatus;
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
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto createAccount(AccountDto account) {

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        setRolesForAccount(account, Roles.ROLE_USER);

        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(account)));
    }

    public AccountDto updateAccount(AccountDto account) {

        String password = accountRepository.findById(account.getId()).get().getPassword();

        account.setPassword(password);
        setRolesForAccount(account, Roles.valueOf(account.getRole()));

        Account updatedAccount = accountRepository.saveAndFlush(accountMapper.toEntity(account));

        return accountMapper.toDto(updatedAccount);
    }


    public void setRolesForAccount(AccountDto account, Roles role) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole(role));

        account.setRoleSet(roles);
    }

    public void changePassword(Account account, String password) {

        account.setPassword(bCryptPasswordEncoder.encode(password));
        accountRepository.saveAndFlush(account);

        log.info("IN UserService changeUserPassword for user {}", account);

    }
}
