package com.fc.projectboard.repository.querydsl;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags(); // 도메인 return이 아니므로 querydsl 사용
}
