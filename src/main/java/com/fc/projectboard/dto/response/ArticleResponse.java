package com.fc.projectboard.dto.response; // 패키지 선언, 이 클래스가 위치할 패키지 경로 지정

import com.fc.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;

// ArticleResponse는 레코드 클래스로, 응답 데이터 구조를 간단하게 정의
public record ArticleResponse(
        Long id, // 게시글의 ID
        String title, // 게시글의 제목
        String content, // 게시글의 내용
        String hashtag, // 게시글에 연결된 해시태그
        LocalDateTime createdAt, // 게시글 생성 시간
        String email, // 게시글 작성자의 이메일
        String nickname // 게시글 작성자의 닉네임
) {

    // 정적 팩토리 메서드, 모든 필드 값을 입력받아 새로운 ArticleResponse 객체를 생성하여 반환
    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtag, createdAt, email, nickname);
    }

    // DTO 객체로부터 ArticleResponse 객체를 생성하는 정적 팩토리 메서드
    public static ArticleResponse from(ArticleDto dto) {
        String nickname = dto.userAccountDto().nickname(); // DTO에서 닉네임을 추출
        if (nickname == null || nickname.isBlank()) { // 닉네임이 비어있거나 null일 경우
            nickname = dto.userAccountDto().userId(); // 대체 정보로 유저 ID 사용
        }

        // 생성자를 통해 ArticleResponse 객체 생성 및 필드 값 설정, 반환
        return new ArticleResponse(
                dto.id(), // 게시글 ID
                dto.title(), // 게시글 제목
                dto.content(), // 게시글 내용
                dto.hashtag(), // 게시글 해시태그
                dto.createdAt(), // 게시글 생성 시간
                dto.userAccountDto().email(), // 게시글 작성자 이메일
                nickname // 계산된 또는 대체된 닉네임
        );
    }

}
