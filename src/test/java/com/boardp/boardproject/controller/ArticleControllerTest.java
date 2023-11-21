package com.boardp.boardproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View Controller Test - 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private final  MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

//    @Disabled("구현중")
    @Test
    @DisplayName("[view][Get] - 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void givenNothing_andRequestArticlesView_thenReturnsArticlesView() throws Exception {

        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));
    }

    @Test
    @DisplayName("[view][Get] - 게시글 상세페이지 - 정상호출")
    public void givenNothing_andRequestArticleView_thenReturnsArticleView() throws Exception {

        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));
    }
    @Disabled("구현중")
    @Test
    @DisplayName("[view][Get] - 게시글 검색 전용페이지 - 정상호출")
    public void givenNothing_andRequestArticleSearchView_thenReturnsArticleSearchView() throws Exception {

        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
    }
    @Disabled("구현중")
    @Test
    @DisplayName("[view][Get] - 게시글 해쉬태그 전용페이지 - 정상호출")
    public void givenNothing_andRequestArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {

        mvc.perform(get("/articles/hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith (MediaType.TEXT_HTML))
                .andExpect(view().name("articles/hashtag"));
    }




}