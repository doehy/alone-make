package com.example.introducemyself.repository;

import com.example.introducemyself.entity.Member;

import java.util.List;

public interface MemberRepository {
     void save(Member member);

    Member findById(Long UserId);

    List<Member> findAll();

    void delete(Member member);
}
