package com.onlytours.service;

import com.onlytours.repository.CompanyRepository;

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



}
