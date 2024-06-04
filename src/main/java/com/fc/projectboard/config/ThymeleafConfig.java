package com.fc.projectboard.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
// @Configuration: 이 클래스가 스프링 설정 클래스임을 나타낸다.
// 스프링 설정 클래스는 주로 @Bean 어노테이션이 붙은 메서드를 가지고 있다.
// 이 메서드들은 스프링이 관리하는 객체(bean)을 정의한다.
@Configuration
public class ThymeleafConfig {

    // @Bean: 이 메서드가 반환하는 객체를 스프링이 관리하는 빈(bean)으로 등록한다.
    // 빈은 스프링이 직접 관리하는 객체로, 여러 군데에서 재사용할 수 있다.
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            // SpringResourceTemplateResolver: Thymeleaf 템플릿을 로드하는 데 사용되는 기본 템플릿 리졸버이다.
            SpringResourceTemplateResolver defaultTemplateResolver,
            // Thymeleaf3Properties: Thymeleaf3의 설정 값을 담고 있는 객체이다.
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        // setUseDecoupledLogic: Thymeleaf3의 분리된 로직 기능을 사용할지 여부를 설정한다.
        // thymeleaf3Properties.isDecoupledLogic(): Thymeleaf3Properties에서 이 기능을 사용할지 여부를 가져온다.
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());

        // 기본 템플릿 리졸버를 반환한다.
        return defaultTemplateResolver;
    }

    // @RequiredArgsConstructor: Lombok 라이브러리를 사용해 모든 final 필드에 대한 생성자를 자동으로 생성한다.
    // @Getter: Lombok 라이브러리를 사용해 모든 필드에 대한 getter 메서드를 자동으로 생성한다.
    // @ConfigurationProperties("spring.thymeleaf3"): spring.thymeleaf3로 시작하는 설정 값을 이 클래스에 매핑한다.
    @RequiredArgsConstructor
    @Getter
    @ConfigurationProperties("spring.thymeleaf3")
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        // decoupledLogic: Thymeleaf3의 분리된 로직 기능을 사용할지 여부를 나타내는 boolean 타입의 변수이다.
        // 이 변수는 외부 설정 파일(application.properties 또는 application.yml)에서 값을 가져온다.
        private final boolean decoupledLogic;
    }
}
