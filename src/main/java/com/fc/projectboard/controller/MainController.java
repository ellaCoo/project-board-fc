package com.fc.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller: 이 클래스가 스프링 MVC의 컨트롤러임을 나타낸다.
// 컨트롤러는 사용자의 요청을 처리하고, 적절한 응답을 반환하는 역할을 한다.
@Controller
public class MainController {

    // @GetMapping: HTTP GET 요청을 처리하는 메서드를 나타낸다.
    // "/" 경로로 GET 요청이 들어오면 이 메서드가 호출된다.
    @GetMapping("/")
    public String root() {
        // "redirect:/articles": 사용자를 "/articles" 경로로 리다이렉트한다.
        // 즉, 사용자가 "/" 경로에 접근하면 "/articles" 경로로 자동으로 이동된다.
        return "redirect:/articles";
    }
}
