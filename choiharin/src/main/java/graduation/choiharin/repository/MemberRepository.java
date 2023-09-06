package graduation.choiharin.repository;

import graduation.choiharin.domain.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "authorities") //lazy 아니고 eager로 authorities 정보 같이 가져옴
    Optional<Member> findOneWithAuthoritiesByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesById(Long id);

    Optional<Member> findByEmail(@Param("email") String email);

}
