package graduation.choiharin.domain.dto.member;

import com.fasterxml.jackson.annotation.*;
import graduation.choiharin.domain.entity.*;
import lombok.*;

import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotNull
    private String membername;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @NotNull
    private String email;

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member) {
        if(member == null) return null;

        return MemberDto.builder()
                .membername(member.getMembername())
                .email(member.getEmail())
                .authorityDtoSet(member.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}

/*    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpRequest {

        @Email
        private String email;

        @NotEmpty
        private String membername;

        @NotEmpty
        private String password;

        @Builder
        public SignUpRequest(@Email String email, @NotEmpty String membername, @NotEmpty String password) {
            this.email = email;
            this.membername = membername;
            this.password = password;
        }

//        public Member toEntity(){
//            return Member.builder()
//                    .email(email)
//                    .membername(membername)
//                    .password(password)
//                    .build();
//        }
    }

    @Getter
    public static class Response {
        private String email;
        private String membername;

//        public Response(Member member) {
//            this.email = member.getEmail().getValue();
//            this.membername = member.getMembername();
//        }
    }*/