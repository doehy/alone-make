package com.example.introducemyself.service;

import com.example.introducemyself.entity.Member;
import com.example.introducemyself.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    public Member findById(Long id) {
        Member findMember = memberRepository.findById(id);
        return findMember;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

}
