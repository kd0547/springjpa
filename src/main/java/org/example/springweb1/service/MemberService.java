package org.example.springweb1.service;

import org.example.springweb1.domain.Member;
import org.example.springweb1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원 가입
         */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //동시성 문제가 생기면 해당 문제가 발생함 > 유니크 제약 조건을 걸어서 DB에 넣기 전에 마지막 검사로 활용
        //EXCEPTION
        List<Member> members= memberRepository.findByNames(member.getUsername());
        if(!members.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    //회원 저회
    public Member findOne(Long memberId) {
        return memberRepository.findMember(memberId);
    }


}
