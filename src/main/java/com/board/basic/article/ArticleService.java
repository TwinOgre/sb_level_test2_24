package com.board.basic.article;

import com.board.basic.user.SiteUser;
import com.board.basic.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    public void create(String title, String content, SiteUser siteUser) {
        Article article = new Article();

        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(siteUser);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);

    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        return article.get();
    }

    public List<Article> getList(String kw) {
        return this.articleRepository.findAllByKeyword(kw);
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());

        this.articleRepository.save(article);
    }

    public void delete(Integer id) {
        this.articleRepository.deleteById(id);
    }
}
