package com.fc.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// @RequestMapping("/articles"): 이 클래스가 "/articles" 경로에 대한 요청을 처리함을 나타낸다.
// 이 클래스 내부의 모든 메서드는 "/articles" 경로를 기본으로 갖는다.
@RequestMapping("/articles")
// @Controller: 이 클래스가 스프링 MVC의 컨트롤러임을 나타낸다.
// 컨트롤러는 사용자의 요청을 처리하고, 적절한 응답을 반환하는 역할을 한다.
@Controller
public class ArticleController {

    // @GetMapping: HTTP GET 요청을 처리하는 메서드를 나타낸다.
    // /articles 경로로 GET 요청이 들어오면 이 메서드가 호출된다.
    @GetMapping
    public String articles(ModelMap map) {
        // map.addAttribute: 모델에 데이터를 추가한다.
        // "articles"라는 이름으로 빈 리스트를 모델에 추가한다.
        // 모델에 추가된 데이터는 뷰(HTML 페이지)에서 사용될 수 있다.
        map.addAttribute("articles", List.of());
        // "articles/index": 뷰의 이름을 반환한다.
        // 즉, "articles/index"라는 이름의 HTML 파일을 찾아서 사용자에게 반환한다.
        return "articles/index";
    }

    // @GetMapping("/{articleId}"): 경로 변수(articleId)를 포함한 GET 요청을 처리하는 메서드를 나타낸다.
    // 예를 들어, /articles/1 같은 요청이 들어오면 이 메서드가 호출된다.
    @GetMapping("/{articleId}")
    public String article(
            // @PathVariable: URL 경로에 있는 변수를 메서드 매개변수에 바인딩한다.
            // 여기서는 URL의 {articleId} 부분을 Long 타입의 articleId 변수에 바인딩한다.
            @PathVariable Long articleId,
            // ModelMap: 뷰에 전달할 데이터를 저장하는 객체이다.
            ModelMap map) {
        // "article"이라는 이름으로 "article" 문자열을 모델에 추가한다.
        // TODO 주석에 따르면 실제 구현 시에는 여기에 실제 데이터를 넣어야 한다.
        map.addAttribute("article", "article"); //TODO:  구현할 때 여기에 실제 데이터 넣어야 한다.
        // "articleComments"라는 이름으로 빈 리스트를 모델에 추가한다.
        map.addAttribute("articleComments", List.of());

        // "articles/detail": 뷰의 이름을 반환한다.
        // 즉, "articles/detail"라는 이름의 HTML 파일을 찾아서 사용자에게 반환한다.
        return "articles/detail";
    }
}

