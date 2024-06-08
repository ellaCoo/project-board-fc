package com.fc.projectboard.config;

import com.fc.projectboard.dto.UserAccountDto;
import com.fc.projectboard.dto.security.BoardPrincipal;
import com.fc.projectboard.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration: 이 클래스가 스프링 설정 클래스임을 나타낸다.
// 스프링 설정 클래스는 주로 @Bean 어노테이션이 붙은 메서드를 가지고 있다.
// 이 메서드들은 스프링이 관리하는 객체(bean)을 정의한다.
@Configuration
public class SecurityConfig {

    @Bean // @Bean: 이 메서드가 반환하는 객체를 스프링이 관리하는 빈(bean)으로 등록한다. 빈은 스프링이 직접 관리하는 객체로, 여러 군데에서 재사용할 수 있다.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // SecurityFilterChain: 스프링 시큐리티에서 HTTP 요청에 대한 보안 필터 체인을 정의하는 객체이다.
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/articles", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/articles/search-hashtag", "GET")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository
                .findById(username)
                .map(UserAccountDto::from)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
