package com.fc.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

// @EnableJpaAuditing: JPA Auditing(감사 기능)을 활성화하기 위한 어노테이션이다.
// JPA Auditing은 엔티티가 생성되거나 수정될 때, 자동으로 날짜와 시간을 기록해준다.
// 예를 들어, 데이터가 언제 만들어졌는지, 언제 수정되었는지 등을 기록할 수 있다.
@EnableJpaAuditing

// @Configuration: 이 클래스가 스프링 설정 클래스임을 나타낸다.
// 스프링 설정 클래스는 주로 @Bean 어노테이션이 붙은 메서드를 가지고 있다.
// 이 메서드들은 스프링이 관리하는 객체(bean)을 정의한다.
@Configuration
public class JpaConfig {

    // @Bean: 이 메서드가 반환하는 객체를 스프링이 관리하는 빈(bean)으로 등록한다.
    // 빈은 스프링이 직접 관리하는 객체로, 여러 군데에서 재사용할 수 있다.
    @Bean
    public AuditorAware<String> auditorAware() {
        // AuditorAware<String>: 현재 감사를 수행하는 사람(감사자)의 이름을 제공하는 인터페이스이다.
        // 감사를 수행하는 사람의 정보를 제공함으로써 누가 데이터를 생성하고 수정했는지를 기록할 수 있다.
        // 여기서는 "ella"라는 이름을 감사자로 반환한다.
        // 주석에 따르면 나중에 스프링 시큐리티로 인증 기능을 붙일 때 수정할 예정이다.
        return () -> Optional.of("ella"); //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }
}
