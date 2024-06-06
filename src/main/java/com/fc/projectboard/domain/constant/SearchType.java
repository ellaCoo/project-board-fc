package com.fc.projectboard.domain.constant;

import lombok.Getter;

// public enum SearchType: 이 선언은 SearchType이라는 이름의 열거형(enum)을 정의한다.
// 열거형(enum)은 미리 정의된 상수 값들의 집합을 나타낸다.
// 여기서는 검색 유형을 나타내는 상수 값들을 정의한다.
public enum SearchType {

    TITLE("제목"),

    CONTENT("본문"),

    ID("유저 ID"),

    NICKNAME("닉네임"),

    HASHTAG("해시태그");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}


