package com.fc.projectboard.domain.type;
// public enum SearchType: 이 선언은 SearchType이라는 이름의 열거형(enum)을 정의한다.
// 열거형(enum)은 미리 정의된 상수 값들의 집합을 나타낸다.
// 여기서는 검색 유형을 나타내는 상수 값들을 정의한다.
public enum SearchType {
    // TITLE: 검색 유형이 제목인 경우를 나타내는 상수 값이다.
    TITLE,
    // CONTENT: 검색 유형이 내용인 경우를 나타내는 상수 값이다.
    CONTENT,
    // ID: 검색 유형이 ID(사용자 식별자)인 경우를 나타내는 상수 값이다.
    ID,
    // NICKNAME: 검색 유형이 닉네임인 경우를 나타내는 상수 값이다.
    NICKNAME,
    // HASHTAG: 검색 유형이 해시태그인 경우를 나타내는 상수 값이다.
    HASHTAG
}


