package graduation.choiharin.service;

import graduation.choiharin.domain.dto.member.*;
import graduation.choiharin.domain.entity.*;
import graduation.choiharin.repository.*;
import graduation.choiharin.utils.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional(readOnly = true) //read 아닌 곳에선 @Transactional 어노테이션 붙여서 사용하기
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 1. 회원가입
     * - 이메일 중복 검증, 비밀번호 암호화, authority 생성
     * -
     * - return(member_id)
     * **/
    @Transactional //c,u,d에서 사용
    public Member signUp(MemberDto memberDto) {
        if(memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }

        /**
         * 실제 DB 연결 시에는 application.yml의 create-drop을 update로 바꾸거나 아예 변경하고
         * 아래 if 문 삭제해도 됨.
         * **/
        Authority authority;

        if (!"admin@naver.com".equals(memberDto.getEmail())) {
            // 이메일이 "admin@naver.com"이 아닌 경우 ROLE_USER를 할당
            authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
        } else {
            // 이메일이 "admin@naver.com"인 경우 ROLE_ADMIN을 할당
            authority = Authority.builder()
                    .authorityName("ROLE_ADMIN")
                    .build();
        }

        Member member = Member.builder()
                .membername(memberDto.getMembername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .email(memberDto.getEmail())
                .authorities(Collections.singleton(authority))
                .status(Status.ACTIVE)
                .build();

        return memberRepository.save(member);
//        memberRepository.save(member);
//        return member.getId(); // 나중에 이거로 수정하기. 엔티티 직접 반환 x
    }

    /**
     * 2. 유저, 권한정보 조회
     * - getMemberWithAuthorities(): email을 기준으로 정보 조회. ADMIN만 가능
     * - getMyMemberWithAuthorities: SecurityContext에 저장된 email 정보 조회. 본인의 정보만 조회 가능
     * **/
    public Optional<Member> getMemberWithAuthorities(String email) {
        return memberRepository.findOneWithAuthoritiesByEmail(email);
    }

    public Optional<Member> getMyMemberWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail);
    }

    /**
     * 3. 로그인, 로그아웃 -> AuthController
     * **/

    /**
     * 4. 회원 정보 수정
     * **/



    /**
     * 5. 회원 탈퇴
     * **/


    /**
     * [기타]
     * 비밀번호 검증 메서드. 로그인, 비밀번호 변경에서 사용
     * **/
    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}

//회원가입 v1
/**
 * //    @Autowired
 * //    private final MemberRepository memberRepository;
 *
 *     @Transactional //c,u,d에서 사용
 *     public Long join(Member member) {
 *         validateDuplicateMember(member);
 *         memberRepository.save(member);
 *         return member.getId();
 *     }
 *
 *     private void validateDuplicateMember(Member member) {
 *         Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
 *         if (findMember.isPresent()) {
 *             throw new IllegalStateException("이미 사용 중인 이메일입니다.");
 *         }
 *     }
 * **/