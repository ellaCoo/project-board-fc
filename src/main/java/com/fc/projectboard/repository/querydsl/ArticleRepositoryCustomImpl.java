package com.fc.projectboard.repository.querydsl;

import com.fc.projectboard.domain.Article;
import com.fc.projectboard.domain.QArticle;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// Impl 붙여주면 querydsl이 인식 가능 (규약)
public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        JPQLQuery<String> query = from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull());

        return query.fetch();
    }
}
