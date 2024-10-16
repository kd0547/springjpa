package org.example.springweb1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    //테스트가 끝나면 트랜잭션 롤백이 되지만 false로 설정하면 DB에 남아 있다.
    //@Rollback(value = false)
    public void testMember() {

        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findMember(saveId);

        //than
        Assertions.assertEquals(findMember.getId(),saveId);

    }


}
