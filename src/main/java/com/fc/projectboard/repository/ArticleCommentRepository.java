package com.fc.projectboard.repository;

import com.fc.projectboard.domain.ArticleComment;
import com.fc.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource // 이 어노테이션은 스프링 데이터 REST를 사용하여 리포지토리에 대한 RESTful 엔드포인트를 자동으로 생성하도록 설정
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>, // JpaRepository를 상속받아 기본적인 CRUD 및 페이징 처리 기능을 제공
        QuerydslPredicateExecutor<ArticleComment>, // Querydsl 지원을 추가하여 쿼리를 타입 세이프하게 생성할 수 있도록 함
        QuerydslBinderCustomizer<QArticleComment>  // Querydsl의 바인딩 커스터마이징을 위한 인터페이스를 구현
{
    List<ArticleComment> findByArticle_Id(Long articleId); // articleId를 사용하여 ArticleComment 객체들을 조회하는 메서드

    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) { // QuerydslBinderCustomizer 인터페이스의 메서드를 오버라이드하여 쿼리 바인딩을 커스터마이즈
        bindings.excludeUnlistedProperties(true); // 명시되지 않은 속성은 바인딩에서 제외
        bindings.including(root.content, root.createdAt, root.createdBy); // content, createdAt, createdBy 속성만 쿼리 바인딩에 포함

        // content 필드에 대해 대소문자를 구분하지 않고 포함하는 필터를 적용
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        // createdAt 필드에 대해 정확히 일치하는 필터를 적용
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        // createdBy 필드에 대해 대소문자를 구분하지 않고 포함하는 필터를 적용
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

