package graduation.choiharin.utils;

import org.slf4j.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {}

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String memberEmail = null; //spring security 기준 username(pk)
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            memberEmail = springSecurityUser.getUsername(); // 만약 오류 발생하면 이 부분 다시 체크. 커스텀 필요할 수 있음
        } else if (authentication.getPrincipal() instanceof String) {
            memberEmail = (String) authentication.getPrincipal();
        }

        //실제로는 Member.email 값이 return
        return Optional.ofNullable(memberEmail);
    }
}
