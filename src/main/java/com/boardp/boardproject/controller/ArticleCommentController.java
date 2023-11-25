package com.boardp.boardproject.controller;

import com.boardp.boardproject.dto.UserAccountDto;
import com.boardp.boardproject.dto.request.ArticleCommentRequest;
import com.boardp.boardproject.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest){
            //Todo : 인증 정보 추가
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "uno","1234","uno@email.com","uno","memo",null,null,null,null
        )));
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
            articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }

}
