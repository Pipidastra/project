package com.onlytours.mapper;

import com.onlytours.dto.CommentDto;
import com.onlytours.entity.Comment;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.CommentRepository;
import com.onlytours.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {

    private final ModelMapper mapper;
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final TourRepository tourRepository;

    @Autowired
    public CommentMapper(ModelMapper mapper, CommentRepository commentRepository, AccountRepository accountRepository,
                         TourRepository tourRepository) {
        super(Comment.class, CommentDto.class);
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.tourRepository = tourRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Comment.class, CommentDto.class)
                .addMappings(m -> m.skip(CommentDto::setUsername)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentDto::setTourId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentDto::setAccountId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommentDto.class, Comment.class)
                .addMappings(m -> m.skip(Comment::setTour)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Comment::setAccount)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Comment source, CommentDto destination) {
        destination.setUsername(getUsername(source));
        destination.setTourId(getTourId(source));
        destination.setAccountId(getAccountId(source));
    }

    private String getUsername(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getName();
    }

    private Long getTourId(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getId();
    }

    private Long getAccountId(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    @Override
    void mapSpecificFields(CommentDto source, Comment destination) {
        destination.setTour(tourRepository.findById(source.getTourId()).orElse(null));
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
    }
}
