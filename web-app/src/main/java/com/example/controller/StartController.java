package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Tour;
import com.example.repository.TourRepository;
import com.example.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

@Controller
@Slf4j
public class StartController {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ModelAndView start() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("welcome");

        return modelAndView;
    }

    @GetMapping(value = "/tours", produces = "application/json")
    @ResponseBody
    public  List<Tour> loadAccounts() {

        List<Tour> tours = tourRepository.findAll();

        return tours;
    }

    @GetMapping(value = "/tour", produces = "application/json")
    @ResponseBody
    public ModelAndView loadAccount(@RequestParam Long id) {

        Tour tour = tourRepository.findById(id).get();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject(tour);
        modelAndView.setViewName("tourPage");
        log.info("here in tour");

        return modelAndView;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Account> loginAccount(@RequestParam("email") String email, @RequestParam("password")String password) {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        log.info("here");
        Account account = accountService.login(email, password);
        if (account != null) {
//            modelAndView.setViewName("showAccounts");
//            modelAndView.addObject(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}