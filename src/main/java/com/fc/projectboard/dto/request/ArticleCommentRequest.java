package com.fc.projectboard.dto.request;

import com.fc.projectboard.dto.ArticleCommentDto;
import com.fc.projectboard.dto.UserAccountDto;

import java.io.Serializable;

/**
 * DTO for {@link com.fc.projectboard.domain.ArticleComment}
 */
public record ArticleCommentRequest(
        Long articleId,
        Long parentCommentId,
        String content
) implements Serializable {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.of(articleId, null, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String content) {
        return new ArticleCommentRequest(articleId, parentCommentId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                parentCommentId,
                content
        );
    }
}