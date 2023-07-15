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
    /**
     * 회원 등록
     */
    @Transactional
    public Member save(Member member) {
        // validateDuplicateMember(member); # 같은 이름에 대해서 중복 처리를 해주고 싶다.
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
