package graduation.choiharin.service;

import graduation.choiharin.domain.entity.*;
import graduation.choiharin.repository.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

/**
 * spring security import해서 사용하느라 이름이 User로 들어감
 * 실질적으론 Member, MemberRepository를 사용함
 * **/

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository; // MemberRepository로 변경

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 로그인에서 username(Member의 pk)이 아니라 email을 사용할 것이므로 parameter를 email로 변경함
     * 원래 spring security에서 username은 테이블의 pk를 의미하고 String type
     * **/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) { // email이 username

        return memberRepository.findOneWithAuthoritiesByEmail(email)
                .map(member -> createUser(email, member))
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, Member member) {
        if (member.getStatus() == Status.INACTIVE) {
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        //콘솔 확인용
        System.out.println("=========================================");
        System.out.println("member = " + member);
        System.out.println("email = " + email);
        System.out.println("grantedAuthorities = " + grantedAuthorities);
        System.out.println("memberRepository = " + memberRepository.findOneWithAuthoritiesByEmail(email));
        System.out.println("member_id = " + memberRepository.findOneWithAuthoritiesByEmail(email).get().getId());
        System.out.println("=====================================================");
        System.out.println(member.getId());
        System.out.println(member.getMembername());
        System.out.println("=========================================");

        /**
         * memberRepository.findOneWithAuthoritiesByEmail(email)) 에서 이미 멤버 엔티티는 가져올 수 있음
         * 아래 User을 custom해서(CustomMember) email 외 다른 필드도 받을 수 있도록 수정했음
         * **/
//        return new org.springframework.security.core.userdetails.User(member.getEmail(),
//                member.getPassword(),
//                grantedAuthorities);
        return new CustomMember(member.getEmail(), member.getPassword(), grantedAuthorities,
                member.getId(), member.getMembername());
    }
}