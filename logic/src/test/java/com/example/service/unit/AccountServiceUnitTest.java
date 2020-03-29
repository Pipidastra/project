package com.example.service.unit;

import com.example.configuration.DatabaseConfig;
import com.example.configuration.SpringMvcConfig;
import com.example.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcConfig.class,
        DatabaseConfig.class})
@WebAppConfiguration
@Slf4j
public class AccountServiceUnitTest {

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void encodingPasswordTest() {

        String password = "12345";
        String encodePassword = bCryptPasswordEncoder.encode(password);

        Assert.assertNotEquals(password, encodePassword);

    }

}
