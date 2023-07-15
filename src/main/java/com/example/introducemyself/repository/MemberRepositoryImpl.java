package com.example.introducemyself.repository;

import com.example.introducemyself.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final EntityManager em;
    @Override
    public void save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
            log.info("merge 되는거아니여?");
        }
    }
    @Override
    public Member findById(Long UserId) {
        return em.find(Member.class, UserId);
    }
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    @Override
    public void delete(Member member) {
        em.createQuery("delete from Member m where m.id = :memberId")
                .setParameter("memberId", member.getId())
                .executeUpdate();
    }
}
