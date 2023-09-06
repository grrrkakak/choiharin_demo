package graduation.choiharin.repository;

import graduation.choiharin.domain.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    List<MemberCard> findByMemberId(Long memberId);

}
