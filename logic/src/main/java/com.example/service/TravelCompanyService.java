package com.example.service;

import com.example.entity.TravelCompany;
import com.example.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Slf4j
@Service
public class TravelCompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public TravelCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

//    public TravelCompany createCompany(TravelCompany travelCompany) {
//
//        TravelCompany company=new TravelCompany();
//
//        company.setName(travelCompany.getName());
//        company.setEmail(travelCompany.getEmail());
//        company.setAddress(travelCompany.getAddress());
//        company.setPhone(travelCompany.getPhone());
//
//        companyCrudService.add(company);
//
//        log.info("IN TravelCompanyService createCompany {}", company);
//
//        return company;
//    }
//
//    public void updateCompany(TravelCompany travelCompany) {
//
//        TravelCompany company=new TravelCompany();
//
//        company.setName(travelCompany.getName());
//        company.setEmail(travelCompany.getEmail());
//        company.setAddress(travelCompany.getAddress());
//        company.setPhone(travelCompany.getPhone());
//
//        companyCrudService.update(company);
//
//    }

}
