package com.example.introducemyself.repository;

import com.example.introducemyself.entity.Comment;

import java.util.List;

public interface CommentRepository{
    Comment save(Comment comment);

    Comment findById(Long id);

    List<Comment> findByMemberId(Long memberId);
    List<Comment> findByNickname(String nickname);

    void delete(Comment comment);
}
