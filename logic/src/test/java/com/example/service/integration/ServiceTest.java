package com.example.service.integration;

import com.example.configuration.DatabaseConfig;
import com.example.configuration.SpringMvcConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcConfig.class,
        DatabaseConfig.class})
@WebAppConfiguration
@Ignore
public class ServiceTest {

}
