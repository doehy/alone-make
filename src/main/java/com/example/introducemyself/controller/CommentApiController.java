package com.example.introducemyself.controller;

import com.example.introducemyself.dto.CommentDto;
import com.example.introducemyself.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    @Autowired
    private final CommentService commentService;

    @GetMapping("/api/friends/{memberId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long memberId) {
        List<CommentDto> commentDto = commentService.showComments(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PostMapping("/api/friends/{memberId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long memberId, @RequestBody CommentDto commentDto) {
        CommentDto CreatedCommentDto = commentService.create(memberId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(CreatedCommentDto);
    }

    @PostMapping("/api/comments/{commentId}/update")
    public ResponseEntity<CommentDto> update(@PathVariable Long commentId, @RequestBody CommentDto commentDto){
        CommentDto updatedCommentDto = commentService.update(commentId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCommentDto);
    }

    @PostMapping("/api/comments/{commentId}/delete")
    public ResponseEntity<CommentDto> delete(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        CommentDto deletedComment = commentService.delete(commentId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(deletedComment);
    }
}
