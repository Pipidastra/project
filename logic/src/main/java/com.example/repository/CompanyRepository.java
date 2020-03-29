package com.example.repository;

import com.example.entity.TravelCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<TravelCompany,Long> {}
