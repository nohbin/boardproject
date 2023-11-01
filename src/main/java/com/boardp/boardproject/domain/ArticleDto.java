package com.boardp.boardproject.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Article}
 */
public record ArticleDto(
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
}