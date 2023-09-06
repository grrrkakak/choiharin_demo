package graduation.choiharin.domain.dto.member;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    /**
     * jwt 관련 클래스에서 username 이라고 써있는데 spring security class import 받아 사용하느라 username으로 작성됨.
     * 실제로 입력할 때는 email을 작성해야 함.
     * **/
    @NotNull
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email; // email 필드 사용

    @NotNull
    private String password;
}
