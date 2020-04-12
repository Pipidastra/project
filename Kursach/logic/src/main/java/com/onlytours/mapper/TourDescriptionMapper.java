package com.onlytours.mapper;

import com.onlytours.dto.TourDescriptionDto;
import com.onlytours.entity.TourDescription;
import com.onlytours.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TourDescriptionMapper extends AbstractMapper<TourDescription,TourDescriptionDto> {

    private final ModelMapper mapper;
    private final TourRepository tourRepository;

    @Autowired
    public TourDescriptionMapper(ModelMapper mapper, TourRepository tourRepository) {

        super(TourDescription.class, TourDescriptionDto.class);
        this.mapper = mapper;
        this.tourRepository = tourRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TourDescription.class, TourDescriptionDto.class)
                .addMappings(m -> m.skip(TourDescriptionDto::setIdTour)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(TourDescriptionDto::setTourName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TourDescriptionDto.class, TourDescription.class)
                .addMappings(m -> m.skip(TourDescription::setTour)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(TourDescription source, TourDescriptionDto destination) {
        destination.setIdTour(getTourId(source));
        destination.setTourName(getTourName(source));
    }

    private Long getTourId(TourDescription source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getId();
    }

    private String getTourName(TourDescription source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getName();
    }


    @Override
    void mapSpecificFields(TourDescriptionDto source, TourDescription destination) {
        destination.setTour(tourRepository.findById(source.getIdTour()).orElse(null));
    }
}
