package graduation.choiharin.config;

import graduation.choiharin.jwt.*;
import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize 어노테이션을 메소드 단위로 사용 가능하게 함
@Configuration //스프링 빈 등록
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다.
//                .requestMatchers("/api/authenticate").permitAll() // 로그인 api
//                .requestMatchers("/api/signup").permitAll() // 회원가입 api
//                .requestMatchers(PathRequest.toH2Console()).permitAll()// h2-console, favicon.ico 요청 인증 무시
//                .requestMatchers("/favicon.ico").permitAll()
//                .anyRequest().authenticated() // 그 외 인증 없이 접근X
                .anyRequest().permitAll() //일단 모두 허용해놓고 기능 개발 끝나면 윗 줄로 수정

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)); // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용

        return httpSecurity.build();
    }

}

/**
 * 웹 버전
 *     @Override
 *     protected void configure(HttpSecurity http) throws Exception {
 *         http
 *                 .authorizeRequests()
 *                 .antMatchers("/api/signUp").
 *     }
 *
 *     //추후 목적에 맞게 경로 수정
 * //    @Override
 * //    protected void configure(HttpSecurity http) throws Exception {
 * //        http
 * //                .authorizeRequests()
 * //                .antMatchers("/public/**").permitAll() // 특정 경로에 대한 접근은 인증 없이 허용
 * //                .anyRequest().authenticated() // 나머지 요청은 인증이 필요
 * //                .and()
 * //                .formLogin() // 기본 로그인 폼 사용
 * //                .loginPage("/login") // 로그인 페이지 경로 지정
 * //                .permitAll() // 로그인 페이지는 인증 없이 접근 가능
 * //                .and()
 * //                .logout() // 로그아웃 설정
 * //                .logoutSuccessUrl("/login?logout") // 로그아웃 후 이동할 경로
 * //                .permitAll(); // 로그아웃은 인증 없이 접근 가능
 * //    }
 * **/

