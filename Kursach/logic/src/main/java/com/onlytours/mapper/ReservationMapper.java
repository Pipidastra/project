package com.onlytours.mapper;

import com.onlytours.dto.ReservationDto;
import com.onlytours.entity.Reservation;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ReservationMapper extends AbstractMapper<Reservation, ReservationDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final TourRepository tourRepository;

    @Autowired
    public ReservationMapper(ModelMapper mapper, AccountRepository accountRepository, TourRepository tourRepository) {
        super(Reservation.class, ReservationDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.tourRepository = tourRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Reservation.class, ReservationDto.class)
                .addMappings(m -> m.skip(ReservationDto::setAccountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setNameTour)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setTourId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ReservationDto.class, Reservation.class)
                .addMappings(m -> m.skip(Reservation::setAccount)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Reservation::setTour)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Reservation source, ReservationDto destination) {
        destination.setAccountId(getAccountId(source));
        destination.setNameTour(getTourName(source));
        destination.setTourId(getTourId(source));
    }

    private Long getAccountId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    private Long getTourId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getId();
    }

    private String getTourName(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getName();
    }


    @Override
    void mapSpecificFields(ReservationDto source, Reservation destination) {
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
        destination.setTour(tourRepository.findById(source.getTourId()).orElse(null));
    }
}
