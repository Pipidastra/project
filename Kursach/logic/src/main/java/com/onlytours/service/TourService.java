package com.onlytours.service;

import com.onlytours.dto.TourDto;
import com.onlytours.entity.Account;
import com.onlytours.entity.Tour;
import com.onlytours.mapper.TourMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourMapper tourMapper, AccountRepository accountRepository) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
        this.accountRepository = accountRepository;
    }


    public Tour loadTourWithComments(Long id) {

        Tour tour = tourRepository.findById(id).get();

        return tour;

    }

    public ResponseEntity save(TourDto tourDto) {

        Optional<Tour> tour = tourRepository.findFirstByName(tourDto.getName());
        if (!tour.isPresent()) {
            Long id = getCurrentUserId();

            tourDto.setRating(-1);
            tourDto.setCompanyId(id);
            tourDto.setImage("/resources/images/imageForTour");

            tourRepository.save(tourMapper.toEntity(tourDto));

            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public TourDto update(TourDto tourDto) {

        tourDto.setCompanyId(getCurrentUserId());
        tourDto.setRating(3);
        tourDto.setImage("/resources/images/imageForTour");

        Tour tour = tourRepository.saveAndFlush(tourMapper.toEntity(tourDto));
        return tourMapper.toDto(tour);
    }

    public ResponseEntity getAll(Pageable pageable, String param) {

        Page<Tour> tours;
        Long id = getCurrentUserId();

        if ("".equals(param)) {
            tours = tourRepository.findAllByCompanyId(id, pageable);
        } else {
            tours = tourRepository.findTours(param, pageable);
        }

        if (tours.getSize() != 0) {
            List<TourDto> toursDto = new ArrayList<>();
            for (Tour tour : tours) {
                toursDto.add(tourMapper.toDto(tour));
            }

            return new ResponseEntity(toursDto, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    public Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
}