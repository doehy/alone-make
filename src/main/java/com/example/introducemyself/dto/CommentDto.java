package com.example.introducemyself.dto;

import com.example.introducemyself.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("member_id")
    private Long memberId;
    private String nickname;
    private String content;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getMember().getId(),
                comment.getNickname(),
                comment.getContent()
        );
    }
}
