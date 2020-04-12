package com.onlytours.repository;

import com.onlytours.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByTourId(Long id, Pageable pageable);
    Optional<Comment> findByAccount_IdAndTourId(Long accountId,Long tourId);
    Long countByTourId(Long tourId);

}
