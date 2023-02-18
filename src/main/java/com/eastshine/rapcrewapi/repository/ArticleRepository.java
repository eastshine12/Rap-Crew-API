package com.eastshine.rapcrewapi.repository;

import com.eastshine.rapcrewapi.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {


}
