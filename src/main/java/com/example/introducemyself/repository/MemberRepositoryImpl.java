package com.example.introducemyself.repository;

import com.example.introducemyself.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public Member findById(Long userId) {
        return em.find(Member.class, userId);
    }

    @Override
    public Member findByUsername(String username) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
                .setParameter("username", username);
        List<Member> results = query.getResultList();
        return results.isEmpty()? null : results.get(0);
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
