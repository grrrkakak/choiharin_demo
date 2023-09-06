package graduation.choiharin.jwt;

import lombok.*;
import org.springframework.security.config.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) {

        // security 로직에 JwtFilter 등록
        http.addFilterBefore(
                new JwtFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
