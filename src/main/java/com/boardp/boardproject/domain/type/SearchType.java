package com.boardp.boardproject.domain.type;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID"),
    NICKNAME("닉네임"),
    HASHTAG("해시태그");

    @Getter
    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
