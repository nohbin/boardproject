package com.boardp.boardproject.service;

import com.boardp.boardproject.domain.Article;
import com.boardp.boardproject.domain.ArticleComment;
import com.boardp.boardproject.dto.ArticleCommentDto;
import com.boardp.boardproject.repository.ArticleCommentRepository;
import com.boardp.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID 로 조회 하면 해당하는 댓글 List Return")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments(){
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                            Article.of("title", "content", "#hashtag"))
        );

        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);

        assertThat(articleComments).isNotNull();
        then(articleCommentRepository).should().findById(articleId);
    }
    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComments_thenSavesArticleComments(){
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        sut.saveArticleComments(ArticleCommentDto.of(LocalDateTime.now(),"nohbin",LocalDateTime.now(),"nohbin","content"));

        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

//    @Test
//    void given_when_then(){
//
//    }
//
//    @Test
//    void given_when_then(){
//
//    }

}