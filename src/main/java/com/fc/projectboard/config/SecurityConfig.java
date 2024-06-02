package com.fc.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration: 이 클래스가 스프링 설정 클래스임을 나타낸다.
// 스프링 설정 클래스는 주로 @Bean 어노테이션이 붙은 메서드를 가지고 있다.
// 이 메서드들은 스프링이 관리하는 객체(bean)을 정의한다.
@Configuration
public class SecurityConfig {

    // @Bean: 이 메서드가 반환하는 객체를 스프링이 관리하는 빈(bean)으로 등록한다.
    // 빈은 스프링이 직접 관리하는 객체로, 여러 군데에서 재사용할 수 있다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain: 스프링 시큐리티에서 HTTP 요청에 대한 보안 필터 체인을 정의하는 객체이다.
        // HttpSecurity: HTTP 보안 설정을 구성하기 위한 객체이다.
        return http
                // authorizeHttpRequests: HTTP 요청에 대한 인가(권한 부여) 설정을 시작한다.
                .authorizeHttpRequests(auth -> auth
                        // anyRequest().permitAll(): 모든 요청에 대해 인가(권한) 없이 접근을 허용한다.
                        // 즉, 모든 사용자가 로그인 없이도 모든 페이지에 접근할 수 있게 한다.
                        .anyRequest().permitAll()
                )
                // formLogin: 기본 폼 로그인을 사용하도록 설정한다.
                // withDefaults(): 기본 설정을 사용한다는 의미이다.
                .formLogin(withDefaults())
                // build(): 설정을 기반으로 SecurityFilterChain 객체를 생성한다.
                .build();
    }
}
