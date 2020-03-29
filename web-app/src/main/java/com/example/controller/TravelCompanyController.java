package com.example.controller;

import com.example.entity.TravelCompany;
import com.example.service.TravelCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/company")
public class TravelCompanyController {


    private final TravelCompanyService travelCompanyService;

    @Autowired
    public TravelCompanyController(TravelCompanyService travelCompanyService) {
        this.travelCompanyService = travelCompanyService;
    }


    @RequestMapping(value = "/addingCompany", method = {RequestMethod.GET})
    public ModelAndView addingCompany(TravelCompany travelCompany) {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addingCompany");

        return modelAndView;
    }

    @RequestMapping(value = "/addingCompany", method = {RequestMethod.POST})
    public ModelAndView addingCompanyPost(TravelCompany travelCompany) {

        ModelAndView modelAndView=new ModelAndView("startPage");

//        travelCompanyService.createCompany(travelCompany);

        return modelAndView;
    }

}
