package com.example.introducemyself.repository;

import com.example.introducemyself.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{
    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            em.persist(comment);
            return comment;
        } else {
            em.merge(comment);
            return comment;
        }
    }

    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> findByMemberId(Long memberId) {
        return em.createQuery("select c from Comment c Where c.member_Id = :memberId", Comment.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<Comment> findByNickname(String nickname) {
        return em.createQuery("select c from Comment c where c.nickname = :nickname", Comment.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public void delete(Comment comment) {
        em.createQuery("SELECT FROM Comment c where c.id = :commentId")
                .setParameter("commentId", comment.getId())
                .executeUpdate();
    }

}
