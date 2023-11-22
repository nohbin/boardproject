package com.boardp.boardproject.service;

import com.boardp.boardproject.domain.Article;
import com.boardp.boardproject.domain.type.SearchType;
import com.boardp.boardproject.dto.ArticleDto;
import com.boardp.boardproject.dto.ArticleUpdateDto;
import com.boardp.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

        /*
        홈버튼 -> 게시판 페이지
        정렬
         */
    @DisplayName("게시글을 검색하면 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 요청 시 게시글 반환")
    @Test
    void givenId_whenSearchingArticle_thenReturnsArticle(){
        ArticleDto articles = sut.searchArticle(1L);
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){

       given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "nohbin", "title", "content", "hashtag"));

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID 와 수정정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenIdAndModifiedInfo_whenSavingArticle_thenUpdateArticle(){

       given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle(){

       willDoNothing().given(articleRepository).delete(any(Article.class));

        sut.deleteArticle(1L);

        then(articleRepository).should().delete(any(Article.class));
    }


}