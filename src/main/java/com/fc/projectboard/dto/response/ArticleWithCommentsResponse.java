package com.fc.projectboard.dto.response;

import com.fc.projectboard.dto.ArticleWithCommentsDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id, // 게시글의 고유 번호(ID)
        String title, // 게시글 제목
        String content, // 게시글 내용
        String hashtag, // 게시글에 사용된 해시태그
        LocalDateTime createdAt, // 게시글이 생성된 시간
        String email, // 게시글 작성자의 이메일 주소
        String nickname, // 게시글 작성자의 닉네임
        Set<ArticleCommentResponse> articleCommentsResponses // 게시글에 달린 댓글 목록, ArticleCommentResponse 객체의 집합
) implements Serializable { // Serializable 인터페이스를 구현하여 객체의 직렬화가 가능하도록 함

    // 정적 팩토리 메서드, 필요한 모든 정보를 받아 ArticleWithCommentResponse 객체를 생성하고 반환
    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtag, createdAt, email, nickname, articleCommentResponses);
    }

    // DTO를 사용해 ArticleWithCommentResponse 객체를 생성하는 정적 팩토리 메서드
    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname(); // DTO에서 사용자 닉네임 가져오기
        if (nickname == null || nickname.isBlank()) { // 닉네임이 null이거나 비어있으면
            nickname = dto.userAccountDto().userId(); // 대체 정보로 사용자 ID 사용
        }

        // 생성자를 통해 새로운 ArticleWithCommentResponse 객체를 생성하고 초기화하여 반환
        return new ArticleWithCommentsResponse(
                dto.id(), // DTO에서 게시글 ID 가져오기
                dto.title(), // DTO에서 게시글 제목 가져오기
                dto.content(), // DTO에서 게시글 내용 가져오기
                dto.hashtag(), // DTO에서 게시글 해시태그 가져오기
                dto.createdAt(), // DTO에서 게시글 생성 시간 가져오기
                dto.userAccountDto().email(), // DTO에서 작성자 이메일 가져오기
                nickname, // 계산된 또는 대체된 닉네임
                dto.articleCommentDtos().stream() // DTO에서 댓글 DTO 스트림 생성
                        .map(ArticleCommentResponse::from) // 스트림의 각 요소를 ArticleCommentResponse 객체로 변환
                        .collect(Collectors.toCollection(LinkedHashSet::new)) // 결과를 LinkedHashSet으로 수집하여 순서 유지
        );
    }

}
