package com.example.introducemyself.controller;

import com.example.introducemyself.dto.MemberDto;
import com.example.introducemyself.entity.Member;
import com.example.introducemyself.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
    private final MemberService memberService;

    /**
     * 등록 페이지
     */
    @GetMapping("/friends/new")
    @Operation()
    public String newFriends() {
        return "/friends/new";
    }

    /**
     * 데이터를 받고 확인 하기 위해 상세페이지로 리다이렉트
     */
    @PostMapping("/friends/create")
    public String saveFriends(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        memberService.save(member);
        return "redirect:/friends/" + member.getId();
    }

    @GetMapping("/friends/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member findMember = memberService.findById(id);
        model.addAttribute("member", findMember);
        return "friends/show";
    }


    @GetMapping("/friends")
    public String showUsers(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "/friends/index";
    }

    @GetMapping("/friends/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member findMember = memberService.findById(id);
        model.addAttribute("member", findMember);
        return "friends/edit";
    }

    @PostMapping("/friends/update")
    public String update(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        log.info(member.getMajor());
        // Member target = memberService.findById(member.getId());
        memberService.save(member); //update 과정에 굳이 join 이라는 함수를 써야하나?
        return "redirect:/friends/" + member.getId();
    }

    @GetMapping("/friends/{id}/delete")
    public String delete(@PathVariable Long id) {
        Member target = memberService.findById(id);
        memberService.delete(target);
        return "redirect:/friends";
    }
}
