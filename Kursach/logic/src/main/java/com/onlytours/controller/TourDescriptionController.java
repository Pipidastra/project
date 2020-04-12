package com.onlytours.controller;

import com.onlytours.dto.TourDescriptionDto;
import com.onlytours.entity.Tour;
import com.onlytours.entity.TourDescription;
import com.onlytours.mapper.TourDescriptionMapper;
import com.onlytours.repository.TourDescriptionRepository;
import com.onlytours.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/description")
@Slf4j
public class TourDescriptionController {

    private final TourDescriptionRepository tourDescriptionRepository;
    private final TourDescriptionMapper tourDescriptionMapper;
    private final TourRepository tourRepository;

    @Autowired
    public TourDescriptionController(TourDescriptionRepository tourDescriptionRepository, TourDescriptionMapper tourDescriptionMapper,
                                     TourRepository tourRepository) {
        this.tourDescriptionRepository = tourDescriptionRepository;
        this.tourDescriptionMapper = tourDescriptionMapper;
        this.tourRepository = tourRepository;
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        tourDescriptionRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity load(@RequestParam Long tourId) {

        List<TourDescription> descriptions = tourDescriptionRepository.findAllByTourId(tourId);
        List<TourDescriptionDto> descriptionsDto = new ArrayList<>();
        if (!descriptions.isEmpty()) {
            for (TourDescription description : descriptions) {
                descriptionsDto.add(tourDescriptionMapper.toDto(description));
            }
            return new ResponseEntity<>(descriptionsDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity save(@RequestBody List<TourDescriptionDto> tourDescription) {

        Optional<Tour> tourOptional;

        for (TourDescriptionDto tourDescriptionDto : tourDescription) {
            tourOptional = tourRepository.findFirstByName(tourDescriptionDto.getTourName());
            if(tourOptional.isPresent()){
                tourDescriptionDto.setIdTour(tourOptional.get().getId());
                tourDescriptionRepository.save(tourDescriptionMapper.toEntity(tourDescriptionDto));

            }

        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value = "/update")
    @Transactional
    public ResponseEntity update(@RequestBody List<TourDescriptionDto> tourDescription) {

        for (TourDescriptionDto tourDescriptionDto : tourDescription) {
            tourDescriptionDto.setIdTour(tourRepository.findFirstByName(tourDescriptionDto.getTourName()).get().getId());
            tourDescriptionRepository.saveAndFlush(tourDescriptionMapper.toEntity(tourDescriptionDto));
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
