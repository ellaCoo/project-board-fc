package com.fc.projectboard.dto.request;

import com.fc.projectboard.dto.ArticleCommentDto;
import com.fc.projectboard.dto.UserAccountDto;

import java.io.Serializable;

/**
 * DTO for {@link com.fc.projectboard.domain.ArticleComment}
 */
public record ArticleCommentRequest(Long articleId, String content) implements Serializable {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }
}