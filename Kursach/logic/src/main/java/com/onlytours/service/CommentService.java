package com.onlytours.service;

import com.onlytours.dto.CommentDto;
import com.onlytours.entity.Account;
import com.onlytours.entity.Comment;
import com.onlytours.entity.Tour;
import com.onlytours.mapper.CommentMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.CommentRepository;
import com.onlytours.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Transactional
@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final TourRepository tourRepository;
    private final CommentMapper commentMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TourRepository tourRepository, CommentMapper commentMapper, AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.tourRepository = tourRepository;
        this.commentMapper = commentMapper;
        this.accountRepository = accountRepository;
    }

    public CommentDto save(CommentDto commentDto) {

        commentDto.setTime(setDate());
        Comment comment = commentRepository.save(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    public CommentDto update(CommentDto commentDto) {

        Long id = getCurrentUserId();

        commentDto.setAccountId(id);
        commentDto.setTime(setDate());

        Comment comment = commentRepository.saveAndFlush(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    private void recountRating(CommentDto comment) {

        Tour tour = tourRepository.findFirstById(comment.getTourId());
        if(tour.getRating()==-1){
            tour.setRating(comment.getRating());
        }

        int rating = tour.getRating();

        rating = Math.round((rating + comment.getRating()) / 2);
        tour.setRating(rating);
        tourRepository.saveAndFlush(tour);
    }

    private String setDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);

        return currentDate;
    }

    private Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
}
