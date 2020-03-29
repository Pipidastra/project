package com.example.service.integration;

import com.example.entity.Comment;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.CommentRepository;
import com.example.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class CommentServiceTest extends ServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @Test
    @Transactional
    public void createCommentTest() {

        Account account=accountRepository.findById(1l).get();

        Comment comment = new Comment();

        comment.setMessage("It's ok!");
        comment.setRating(9);
        comment.setTourId(1L);
        comment.setAccount(account);
        commentRepository.saveAndFlush(comment);

        Comment comment2 = new Comment();

        comment2.setMessage("It's ok!!!");
        comment2.setRating(9);
        comment2.setTourId(1L);
        comment2.setAccount(account);
        commentRepository.saveAndFlush(comment2);

        Assert.assertEquals(2, account.getComments().size());

    }
}
