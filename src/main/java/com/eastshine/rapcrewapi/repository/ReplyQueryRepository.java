package com.eastshine.rapcrewapi.repository;

import com.eastshine.rapcrewapi.domain.Reply;
import com.eastshine.rapcrewapi.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyQueryRepository {

    private final EntityManager em;

    public List<ReplyDto> findReply(Long articleId) {
        return em.createQuery(
                "select new com.eastshine.rapcrewapi.dto.ReplyDto(u.nickname, r.content, r.createdAt, r.updatedAt)" +
                        " from Reply r" +
                        " join r.user u" +
                        " where r.article.id = :articleId", ReplyDto.class)
                            .setParameter("articleId", articleId)
                            .getResultList();
    }

}
