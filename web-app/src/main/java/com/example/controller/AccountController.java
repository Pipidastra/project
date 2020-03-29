package com.example.controller;

import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {


    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;


    @GetMapping(value = "/registration")
    public ModelAndView registration() {

        ModelAndView modelAndView = new ModelAndView("registration");

        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registrationAccount(Account account) {

        ModelAndView modelAndView = new ModelAndView("startPage");
        accountService.createAccount(account);

        return modelAndView;
    }

    @PostMapping(value = "/saveAfterUpdate")
    public ModelAndView updateAccount(Account account) {

        ModelAndView modelAndView = new ModelAndView("startPage");
        accountService.updateAccount(account);

        return modelAndView;
    }


    @GetMapping(value = "/loadAccounts", produces = "application/json")
    @ResponseBody
    public List<Account> loadAccounts() {

        List<Account> accounts = accountRepository.findAll();

        return accounts;
    }

    @PostMapping(value = "/delete")
    public ResponseEntity deleteAccount(@RequestParam Long id) {

        accountRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/update", produces = "application/json")
    @ResponseBody
    public Account loadAccount(@RequestParam Long id) {

        Account account = accountRepository.findById(id).get();

        return account;
    }
}
