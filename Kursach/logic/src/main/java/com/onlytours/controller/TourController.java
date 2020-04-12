package com.onlytours.controller;

import com.onlytours.dto.TourDto;
import com.onlytours.entity.Account;
import com.onlytours.entity.Tour;
import com.onlytours.mapper.TourDescriptionMapper;
import com.onlytours.mapper.TourMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.TourDescriptionRepository;
import com.onlytours.repository.TourRepository;
import com.onlytours.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TourController {

    private final TourMapper tourMapper;
    private final TourService tourService;
    private final TourRepository tourRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TourController(TourMapper tourMapper, TourService tourService,
                          TourRepository tourRepository, AccountRepository accountRepository) {
        this.tourMapper = tourMapper;
        this.tourService = tourService;
        this.tourRepository = tourRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/tour/{id}", produces = "application/json")
    @ResponseBody
    public ModelAndView loadTour(@PathVariable Long id) {

        Tour tour = tourService.loadTourWithComments(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(tour);
        modelAndView.setViewName("tourPage");

        return modelAndView;
    }

    @RequestMapping(value = "/tour/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteTour(@PathVariable Long id) {

        tourRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/tours", produces = "application/json")
    @ResponseBody
    public List<Tour> loadTours() {

        List<Tour> tours = tourRepository.findAll();

        return tours;
    }

    @GetMapping(value = "/tour", produces = "application/json")
    @ResponseBody
    public List<Tour> loadTypedTours(@RequestParam String type) {

        List<Tour> tours = tourRepository.findAllByType(type);

        return tours;
    }

    @PostMapping(value = "/tour/save")
    public ResponseEntity save(@RequestBody TourDto tourDto) {

        if (tourDto.getName().equals("") || tourDto.getCountry().equals("") || tourDto.getExitDate().equals("") ||
                tourDto.getNumberDays().equals("") || tourDto.getCost().equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return tourService.save(tourDto);
        }
    }

    @GetMapping(value = "/tours/show", produces = "application/json")
    public ResponseEntity showTours(@RequestParam String param, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return tourService.getAll(pageable, param);
//        Page<Tour> tours;
//        Long id = tourService.getCurrentUserId();
//
//        if ("".equals(param)) {
//            tours = tourRepository.findAllByCompanyId(id, pageable);
//        } else {
//            tours = tourRepository.findTours(param, pageable);
//        }
//
//        if (tours.getSize() != 0) {
//            List<TourDto> toursDto = new ArrayList<>();
//            for (Tour tour : tours) {
//                toursDto.add(tourMapper.toDto(tour));
//            }
//
//            return new ResponseEntity(toursDto, HttpStatus.OK);
//        }
//
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/showTours")
    public ModelAndView showTours() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("toursTablePage");


        return modelAndView;
    }

    @GetMapping(value = "/showTours/{type}")
    public ModelAndView showTypedTours(@PathVariable String type) {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if ("bus".equals(type)) {
            modelAndView.setViewName("busTourPage");
        } else if ("air".equals(type)) {
            modelAndView.setViewName("airTourPage");
        } else if ("lastOffer".equals(type)) {
            modelAndView.setViewName("airTourPage");
        }

        return modelAndView;
    }

    @GetMapping(value = "/tour/update/{id}", produces = "application/json")
    @ResponseBody
    public Tour loadTourForUpdate(@PathVariable Long id) {

        Tour tour = tourRepository.findById(id).get();
        return tour;
    }

    @PostMapping(value = "/tour/update")
    public ResponseEntity updateTour(@RequestBody TourDto tour) {

        tourService.update(tour);

        return new ResponseEntity(HttpStatus.OK);
    }


}
