package com.onlytours.service.integration;

import com.onlytours.dto.AccountDto;
import com.onlytours.entity.Account;
import com.onlytours.mapper.AccountMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
public class AccountServiceTest extends ServiceTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;

    @Test
    @Transactional
    public void createAccountTest() {

        AccountDto account = new AccountDto();
        account.setName("Dashka");
        account.setPhone("+3753330112248");
        account.setPassword("111");
        account.setEmail("ffft88");

        accountService.createAccount(account);
        Assert.assertEquals(3,accountRepository.findAll().size());

    }


    @Test
    @Transactional
    public void updateAccountTest() {

        Account account = accountRepository.findById(1l).get();
        account.setName("Daria");
        log.info(account.getRoleSet().iterator().next().getRole().name());
        accountService.updateAccount(accountMapper.toDto(account));

        Assert.assertEquals("Daria",accountRepository.findById(account.getId()).get().getName());

    }

    @Test
    @Transactional
    public void changePasswordTest() {

        Account account = accountRepository.findById(2l).get();
        String password=account.getPassword();

        accountService.changePassword(account,"12345");
        Assert.assertNotEquals(password,accountRepository.findById(2L).get().getPassword());

    }

}
