package com.example.introducemyself.dto;

import com.example.introducemyself.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String major;
    private String mbti;
    private String roles;
    public Member toEntity() {
        return new Member(id, username, password, major, mbti, password);
    }
}
