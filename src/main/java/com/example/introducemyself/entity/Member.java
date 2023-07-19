package com.example.introducemyself.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String major;

    @Column
    private String mbti;


    public void patch(Member member) {
        if (member.name != null) {
            this.name = member.name;;
        }
        if (member.major != null) {
            this.major = member.major;
        }
        if (member.mbti != null) {
            this.mbti = member.mbti;
        }
    }
}
