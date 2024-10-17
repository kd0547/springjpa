package org.example.springweb1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springweb1.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    private final EntityManager em;
    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member ){
        em.persist(member);
    }

    public Member findMember(Long id) {
        return em.find(Member.class,id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m From Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByNames(String name) {
        return em.createQuery(
                "select m FROM Member m where m.username = :name"
                        , Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
