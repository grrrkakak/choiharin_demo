package graduation.choiharin.controller;

import graduation.choiharin.domain.dto.member.*;
import graduation.choiharin.jwt.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.validation.*;

/**
 * 로그인, 로그아웃 시 jwt과 SecurityContext 관련 Controller
 * AuthService.java가 따로 없어서 controller에서 로직 작성
 * **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 1. 로그인
     * - jwt 발급해서 http 헤더에 넣음
     * - email, password로 로그인 함
     * **/
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

//        // 사용자 정보를 가져온다. (예: Member 엔티티를 조회해서)
//        Member member = loadMemberInfo(loginDto.getEmail()); // 이 부분을 적절히 구현
//
//        // 로그인 응답에 member_id, member_name을 추가
//        LoginResponse response = new LoginResponse(jwt, member.getId(), member.getMembername());
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);


        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        //콘솔 확인용
        System.out.println("=====================================");
        System.out.println("httpHeaders = " + httpHeaders);
        System.out.println("jwt = " + jwt);
        System.out.println("authentication = " + authentication);
        System.out.println("authenticationToken = " + authenticationToken);
        System.out.println("=====================================");

        // tokenDto를 이용해 response body에도 넣어서 리턴
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * 2. 로그아웃
     * - 클라이언트 측에서 저장된 토큰을 삭제하거나 무효화하는 추가 작업 필요
     * **/
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
