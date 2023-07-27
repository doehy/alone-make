package com.example.introducemyself.entity;

import com.example.introducemyself.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column
    private String nickname;
    @Column
    private String content;

    public static Comment createComment(CommentDto commentDto, Member member) {
        if (commentDto.getId() != member.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }
        return new Comment(
            commentDto.getId(),
            member,
            commentDto.getNickname(),
            commentDto.getContent()
        );
    }
}
