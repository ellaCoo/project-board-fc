package com.fc.projectboard.controller;

import com.fc.projectboard.domain.type.SearchType;
import com.fc.projectboard.dto.response.ArticleResponse;
import com.fc.projectboard.dto.response.ArticleWithCommentsResponse;
import com.fc.projectboard.service.ArticleService;
import com.fc.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
// @Controller: 이 클래스가 스프링 MVC의 컨트롤러임을 나타낸다.
// 컨트롤러는 사용자의 요청을 처리하고, 적절한 응답을 반환하는 역할을 한다.
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());

        return "articles/index";
    }

    // @GetMapping("/{articleId}"): 경로 변수(articleId)를 포함한 GET 요청을 처리하는 메서드를 나타낸다.
    @GetMapping("/{articleId}")
    public String article(
            @PathVariable Long articleId,
            ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponses());
        map.addAttribute("totalCount", articleService.getArticleCount());
        return "articles/detail";
    }
}

