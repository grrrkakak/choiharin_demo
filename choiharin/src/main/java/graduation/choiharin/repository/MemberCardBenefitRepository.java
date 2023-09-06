package graduation.choiharin.repository;

import graduation.choiharin.domain.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface MemberCardBenefitRepository extends JpaRepository<MemberCardBenefit, Long> {
    List<MemberCardBenefit> findByMemberId(Long memberId);

    List<MemberCardBenefit> findByCardId(Long cardId);

    List<MemberCardBenefit> findByMemberIdAndCardId(Long memberId, Long cardId);

}
