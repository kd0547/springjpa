package org.example.springweb1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springweb1.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findMember(Long id) {
        return em.find(Member.class,id);
    }


}
