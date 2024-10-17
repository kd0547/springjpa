package org.example.springweb1.service;

import org.example.springweb1.domain.Member;
import org.example.springweb1.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setUsername("kim");

        Long saveId = memberService.join(member);
        Assertions.assertEquals(saveId,memberRepository.findMember(saveId).getId());
    }

    @Test()
    public void 중복_회원_예외() throws Exception {
        Member kim1 = new Member();
        kim1.setUsername("kim");

        Member kim2 = new Member();
        kim2.setUsername("kim");

        memberService.join(kim1);
        // assertThrows를 사용하여 예외가 발생하는지 검증
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(kim2); // 여기서 예외가 발생해야 한다.
        }, "예외가 발생해야 한다.");
    }

}