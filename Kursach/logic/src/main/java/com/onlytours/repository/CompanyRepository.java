package com.onlytours.repository;

import com.onlytours.entity.TravelCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<TravelCompany,Long> {}
