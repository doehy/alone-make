package com.example.introducemyself.controller;

import com.example.introducemyself.dto.MemberDto;
import com.example.introducemyself.entity.Member;
import com.example.introducemyself.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {
    @Autowired
    private final MemberService memberService;

    @GetMapping("/api/friends/{id}")
    public Member show(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PostMapping("/api/friends")
    public ResponseEntity<Member> create(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity();
        memberService.save(member);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping("/api/friends")
    @ApiOperation(value="가입한 멤버 조회")
    public List<Member> index(){
        return memberService.findAll();
    }

    @PatchMapping("/api/friends")
    public Member update(@RequestBody MemberDto memberDto) {
        Member updatedMember = memberDto.toEntity();
        return memberService.save(updatedMember);
    }

    @DeleteMapping("/api/friends{id}")
    public ResponseEntity<Member> delete(@PathVariable Long id) {
        Member deletedMember = memberService.findById(id);
        memberService.delete(deletedMember);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}