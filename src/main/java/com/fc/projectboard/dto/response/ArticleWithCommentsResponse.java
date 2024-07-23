package com.fc.projectboard.dto.response;

import com.fc.projectboard.dto.ArticleCommentDto;
import com.fc.projectboard.dto.ArticleWithCommentsDto;
import com.fc.projectboard.dto.HashtagDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id, // 게시글의 고유 번호(ID)
        String title, // 게시글 제목
        String content, // 게시글 내용
        Set<String> hashtags,
        LocalDateTime createdAt, // 게시글이 생성된 시간
        String email, // 게시글 작성자의 이메일 주소
        String nickname, // 게시글 작성자의 닉네임
        String userId,
        Set<ArticleCommentResponse> articleCommentsResponse // 게시글에 달린 댓글 목록, ArticleCommentResponse 객체의 집합
) {

    // 정적 팩토리 메서드, 필요한 모든 정보를 받아 ArticleWithCommentResponse 객체를 생성하고 반환
    public static ArticleWithCommentsResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtags, createdAt, email, nickname, userId, articleCommentResponses);
    }

    // DTO를 사용해 ArticleWithCommentResponse 객체를 생성하는 정적 팩토리 메서드
    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname(); // DTO에서 사용자 닉네임 가져오기
        if (nickname == null || nickname.isBlank()) { // 닉네임이 null이거나 비어있으면
            nickname = dto.userAccountDto().userId(); // 대체 정보로 사용자 ID 사용
        }

        // 생성자를 통해 새로운 ArticleWithCommentResponse 객체를 생성하고 초기화하여 반환
        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtagDtos().stream()
                        .map(HashtagDto::hashtagName)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname, // 계산된 또는 대체된 닉네임
                dto.userAccountDto().userId(),
                organizeChildComments(dto.articleCommentDtos())
        );
    }

    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos) {
        Map<Long, ArticleCommentResponse> map = dtos.stream()
                .map(ArticleCommentResponse::from)
                .collect(Collectors.toMap(ArticleCommentResponse::id, Function.identity()));

        map.values().stream()
                .filter(ArticleCommentResponse::hasParentComment)
                .forEach(comment -> {
                    ArticleCommentResponse parentComment = map.get(comment.parentCommentId());
                    parentComment.childComments().add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment())
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(ArticleCommentResponse::createdAt)
                                .reversed()
                                .thenComparingLong(ArticleCommentResponse::id)
                        )
                ));
    }
}
