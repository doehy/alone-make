package com.example.introducemyself.dto;

import com.example.introducemyself.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MemberDto {
    private Long id;
    private String name;
    private String major;
    private String mbti;

    public Member toEntity() {
        return new Member(id, name, major, mbti);
    }
}
