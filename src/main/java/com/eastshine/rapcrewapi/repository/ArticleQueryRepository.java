package com.eastshine.rapcrewapi.repository;

import com.eastshine.rapcrewapi.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {

    private final EntityManager em;

    public List<Article> findArticle() {
        return em.createQuery(
                "select a from Article a" +
                        " join fetch a.user u", Article.class).getResultList();
    }

}
