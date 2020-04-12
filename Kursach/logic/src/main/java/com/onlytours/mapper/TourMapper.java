package com.onlytours.mapper;

import com.onlytours.dto.TourDto;
import com.onlytours.entity.Tour;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TourMapper extends AbstractMapper<Tour, TourDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public TourMapper(ModelMapper mapper, AccountRepository accountRepository) {
        super(Tour.class, TourDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Tour.class, TourDto.class)
                .addMappings(m -> m.skip(TourDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TourDto.class, Tour.class)
                .addMappings(m -> m.skip(Tour::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Tour source, TourDto destination) {
        destination.setCompanyId(getAccountId(source));
    }

    private Long getAccountId(Tour source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    @Override
    void mapSpecificFields(TourDto source, Tour destination) {
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));
    }
}
