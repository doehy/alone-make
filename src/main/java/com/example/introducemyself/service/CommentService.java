package com.example.introducemyself.service;

import com.example.introducemyself.dto.CommentDto;
import com.example.introducemyself.entity.Comment;
import com.example.introducemyself.entity.Member;
import com.example.introducemyself.repository.CommentRepository;
import com.example.introducemyself.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public CommentDto create(Long memberId, CommentDto commentDto) {
        Member member = memberRepository.findById(memberId);
        Comment comment = Comment.createComment(commentDto, member);
        Comment savedComment = commentRepository.save(comment);
        return CommentDto.createCommentDto(savedComment);
    }

    public List<CommentDto> showComments(Long memberId) {
        return commentRepository.findByMemberId(memberId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto update(Long commentId, CommentDto commentDto) {
        Comment targetComment = commentRepository.findById(commentId);
        if (targetComment == null) {
            throw new IllegalArgumentException();
        }
        Comment savedComment = commentRepository.save(targetComment);
        return CommentDto.createCommentDto(targetComment);
    }

    @Transactional
    public CommentDto delete(Long commentId, CommentDto commentDto) {
        Comment targetComment = commentRepository.findById(commentId);
        if (targetComment == null) {
            throw new IllegalArgumentException();
        }
        commentRepository.delete(targetComment);
        return CommentDto.createCommentDto(targetComment);
    }
}
