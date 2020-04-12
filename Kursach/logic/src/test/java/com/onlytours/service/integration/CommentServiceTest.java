package com.onlytours.service.integration;

import com.onlytours.dto.CommentDto;
import com.onlytours.entity.Account;
import com.onlytours.mapper.CommentMapper;
import com.onlytours.repository.AccountRepository;
import com.onlytours.repository.CommentRepository;
import com.onlytours.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CommentServiceTest extends ServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CommentMapper commentMapper;

    @Test
    @Transactional
    public void createCommentTest() {

        Account account=accountRepository.findById(1l).get();

        CommentDto comment = new CommentDto();

        comment.setMessage("It's ok!");
        comment.setRating(9);
        comment.setTourId(1L);
        comment.setUsername(account.getName());
        comment.setAccountId(account.getId());
        commentRepository.saveAndFlush(commentMapper.toEntity(comment));

        CommentDto comment2 = new CommentDto();

        comment2.setMessage("It's ok!!!");
        comment2.setRating(9);
        comment2.setTourId(1L);
        comment2.setUsername(account.getName());
        comment2.setAccountId(account.getId());
        commentRepository.saveAndFlush(commentMapper.toEntity(comment2));

        Assert.assertEquals(2, account.getComments().size());

    }
}
