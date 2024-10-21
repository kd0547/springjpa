package org.example.springweb1.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springweb1.domain.Address;
import org.example.springweb1.domain.Member;
import org.example.springweb1.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMemberForm(Model model) {
        log.info("유저 회원가입");
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if(result.hasErrors()) {
            log.warn("에러 있음");
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(),memberForm.getStreet(),memberForm.getZipcode());
        Member member = new Member();
        member.setUsername(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }

}
