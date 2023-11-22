package com.boardp.boardproject.service;

import com.boardp.boardproject.domain.type.SearchType;
import com.boardp.boardproject.dto.ArticleDto;
import com.boardp.boardproject.dto.ArticleUpdateDto;
import com.boardp.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(long l, ArticleUpdateDto dto) {
    }

    public void deleteArticle(long l) {
    }
}
