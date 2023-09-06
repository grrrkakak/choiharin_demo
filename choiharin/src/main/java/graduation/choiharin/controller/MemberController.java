package graduation.choiharin.controller;

import graduation.choiharin.domain.dto.member.*;
import graduation.choiharin.domain.entity.*;
import graduation.choiharin.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    /**
     * 1. 회원가입
     * **/
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.signUp(memberDto));
    }

    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Member> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities().get());
    }

    @GetMapping("/member/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Member> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(email).get());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
