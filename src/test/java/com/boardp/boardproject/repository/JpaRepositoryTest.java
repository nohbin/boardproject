package com.boardp.boardproject.repository;

import com.boardp.boardproject.config.JpaConfig;
import com.boardp.boardproject.domain.Article;
import com.boardp.boardproject.domain.UserAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
@DisplayName("JPA 연결 테스트")
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository,
            @Autowired UserAccountRepository userAccountRepository)
    {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("Select Test")
    @Test
    void givenTestDate_whenSelecting_thenWorksFine(){
        // Given

        // When

        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("Insert Test")
    @Test
    void givenTestDate_whenInserting_thenWorksFine(){
        // Given
        Long previousCount = articleRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("uno", "pw", null, null, null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");

        // When
        articleRepository.save(article);
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("update Test")
    @Test
    void givenTestDate_whenUpdating_thenWorksFine(){
        // Given
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("uno", "pw", null, null, null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");
        articleRepository.save(article);
        articleRepository.findById(1L).orElseThrow();
        String updatingHashtag = "#Springboot";
        article.setHashtag(updatingHashtag);

        // When
//        Article article = Article.of("new article", "new content", "#Spring");
        Article savedArticle = articleRepository.saveAndFlush(article);

        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatingHashtag);

    }

    @DisplayName("delete Test")
    @Test
    void givenTestDate_whenDeleting_thenWorksFine(){
        // Given
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("uno", "pw", null, null, null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");
        articleRepository.save(article);
        articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();



        // When
//      Article article = Article.of("new article", "new content", "#Spring");
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    }

}