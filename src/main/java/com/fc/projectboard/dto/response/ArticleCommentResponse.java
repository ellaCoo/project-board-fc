package com.fc.projectboard.dto.response; // 패키지 선언, DTO의 응답 클래스들을 모아둔 패키지 경로

import com.fc.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;

// ArticleCommentResponse 는 응답 데이터 구조를 정의하는 레코드 클래스
public record ArticleCommentResponse(
        Long id, // 댓글의 ID
        String content, // 댓글의 내용
        LocalDateTime createdAt, // 댓글이 생성된 시간
        String email, // 댓글 작성자의 이메일
        String nickname,
        String userId
) {

    // 정적 팩토리 메서드, 객체 생성과 초기화를 동시에 처리하여 새로운 ArticleCommentResponse 객체를 반환
    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname, userId);
    }

    // DTO로부터 ArticleCommentResponse 객체를 생성하는 정적 팩토리 메서드
    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String nickname = dto.userAccountDto().nickname(); // DTO에서 닉네임을 가져옴
        if (nickname == null || nickname.isBlank()) { // 닉네임이 null이거나 공백인 경우
            nickname = dto.userAccountDto().userId(); // 유저 ID를 닉네임으로 사용
        }

        // 새 ArticleCommentResponse 객체를 생성하고 반환
        return new ArticleCommentResponse(
                dto.id(), // 댓글 ID
                dto.content(), // 댓글 내용
                dto.createdAt(), // 댓글 생성 시간
                dto.userAccountDto().email(), // 댓글 작성자 이메일
                nickname,
                dto.userAccountDto().userId()
        );
    }

}
