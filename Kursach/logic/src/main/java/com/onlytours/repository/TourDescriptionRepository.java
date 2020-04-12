package com.onlytours.repository;

import com.onlytours.entity.TourDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourDescriptionRepository  extends JpaRepository<TourDescription,Long> {

    List<TourDescription> findAllByTourId(Long idTour);
}
