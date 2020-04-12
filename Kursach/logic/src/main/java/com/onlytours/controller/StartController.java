package com.onlytours.controller;

import com.onlytours.dto.AccountDto;
import com.onlytours.repository.TourRepository;
import com.onlytours.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.security.Principal;

@RestController
@Slf4j
public class StartController {

    private final AccountService accountService;

    @Autowired
    public StartController(AccountService accountService) {

        this.accountService = accountService;
    }

    @GetMapping("/403")
    public ModelAndView deniedAccess() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("403");

        return modelAndView;
    }

    @GetMapping(value = "/")
    public ModelAndView start(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "login.error");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }
        modelAndView.setViewName("welcomePage");

        return modelAndView;

    }

    @PostMapping(value = "/registration")
    public ResponseEntity registration(@RequestBody AccountDto account) {

        accountService.createAccount(account);

        return new ResponseEntity(HttpStatus.OK);
    }


}