package com.fc.projectboard.config;

import com.fc.projectboard.domain.UserAccount;
import com.fc.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        // 각 테스트 실행 직전에 인증정보 넣어주라고 작성함
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
            "ellaTest",
            "pw",
            "ella-test@mail.com",
            "ella-test",
            "test memmo"
        )));

    }
}
