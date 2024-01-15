package com.basic.rest.article;

import com.basic.rest.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public RsData<Article> create(String title, String subject) {
        Article a = new Article();

        a.setContent(title);
        a.setSubject(subject);
        a.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(a);

        return RsData.of("S-2", "게시물이 생성되었습니다.", a);
    }

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        if (optionalArticle.isEmpty()) {
            throw  new RuntimeException("없음");
        }

        return optionalArticle.get();
    }


    public RsData<Article> modify(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);

        articleRepository.save(article);

        return RsData.of("S-3", "게시물이 수정되었습니다.", article);
    }

    public RsData<Article> delete(Article article) {
        this.articleRepository.delete(article);
        return RsData.of("S-3", "게시물이 삭제되었습니다.", article);

    }
}
