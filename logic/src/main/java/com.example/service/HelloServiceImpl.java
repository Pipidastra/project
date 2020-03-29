package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    public String showMessage() {
        return "Hello!!!";
    }
}

