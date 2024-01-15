package com.basic.rest.article;

import com.basic.rest.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticlesResponse {
        private final List<Article> articles;
    }


    @GetMapping("")
    public RsData<ArticlesResponse> articles() {
       List<Article> articles = this.articleService.getList();

       return RsData.of("S-1", "성공", new ArticlesResponse(articles));
    }

    @AllArgsConstructor
    @Getter
    public static class ArticleResponse {
        private final Article article;
    }

    @GetMapping("/{id}")
    public RsData<ArticleResponse> article(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);

        return RsData.of("S-2", "성공", new ArticleResponse(article));
    }

    @AllArgsConstructor
    @Getter
    public static class WriteResponse {
        private final Article article;
    }

    @Data
    public static class WriteRequest {
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @PostMapping("")
    public RsData<Article> write(@Valid @RequestBody WriteRequest writeRequest) {

        RsData<Article> RsArticle = this.articleService.create(writeRequest.getSubject(), writeRequest.getContent());

        return RsArticle;
    }

    @AllArgsConstructor
    @Getter
    public static class ModifyResponse {
        private final Article article;
    }

    @Data
    public static class ModifyRequest {
        private String subject;
        private String content;
    }

    @PatchMapping("/{id}")
    public RsData<Article> modify(@Valid @RequestBody WriteRequest writeRequest, @PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);

        RsData<Article> modifyArticle = articleService.modify(article, writeRequest.getSubject(), writeRequest.getContent());

        return modifyArticle;
    }


    @DeleteMapping("/{id}")
    public RsData<Article> delete(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);
        RsData<Article> articleRsData = this.articleService.delete(article);
        return articleRsData;
    }


}
