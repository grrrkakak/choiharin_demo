package graduation.choiharin.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomMember extends User {

    private final Long memberId;
    private final String membername;

    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities, Long memberId, String membername) {
        super(username, password, authorities);
        this.memberId = memberId;
        this.membername = membername;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMembername() {
        return membername;
    }
}



