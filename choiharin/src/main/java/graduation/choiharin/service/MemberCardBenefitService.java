package graduation.choiharin.service;

import graduation.choiharin.domain.entity.*;
import graduation.choiharin.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCardBenefitService {

    @Autowired
    private final MemberCardBenefitRepository memberCardBenefitRepository;

    // 이거 수정해야 할 수도..
    public Optional<MemberCardBenefit> getMemberCardBenefitById(Long memberCardBenefitId) {
        return memberCardBenefitRepository.findById(memberCardBenefitId);
    }

    public List<MemberCardBenefit> getMemberCardBenefitsByMemberId(Long memberId) {
        return memberCardBenefitRepository.findByMemberId(memberId);
    }

    // 혜택의 횟수와 금액을 체크하여 일일 및 월별 제한을 넘지 않도록 처리하는 메소드
    public String isBenefitWithinLimit(MemberCardBenefit memberCardBenefit) {
        // 여기서 혜택의 횟수와 금액을 조회하고, 일일 및 월별 제한을 비교하여 처리합니다.
        // 예를 들어, memberCardBenefit.getDailyDiscountCount(), memberCardBenefit.getDailyDiscountAmount() 등을 활용합니다.

        // 예시: 일일 제한을 체크하는 코드
        if (memberCardBenefit.getDailyDiscountCount() <= memberCardBenefit.getBenefit().getDailyCountLimit() &&
                memberCardBenefit.getDailyDiscountAmount() <= memberCardBenefit.getBenefit().getDailyAmountLimit()) {
            return memberCardBenefit.getCard().getCardname() + " 사용 가능합니다.";
        } else {
            return memberCardBenefit.getCard().getCardname() + " 해당 혜택을 모두 사용했습니다.";
        }
    }


    public void updateDiscountCount(MemberCardBenefit memberCardBenefit, int dailyCount, int monthlyCount, int annuallyCount) {
        memberCardBenefit.setDailyDiscountCount(memberCardBenefit.getDailyDiscountCount() + dailyCount);
        memberCardBenefit.setMonthlyDiscountCount(memberCardBenefit.getMonthlyDiscountCount() + monthlyCount);
        memberCardBenefit.setAnnuallyDiscountCount(memberCardBenefit.getAnnuallyDiscountCount() + annuallyCount);

        memberCardBenefitRepository.save(memberCardBenefit);
    }

    public void updateAccumulationCount(MemberCardBenefit memberCardBenefit, int dailyCount, int monthlyCount, int annuallyCount) {
        memberCardBenefit.setDailyAccumulationCount(memberCardBenefit.getDailyAccumulationCount() + dailyCount);
        memberCardBenefit.setMonthlyAccumulationCount(memberCardBenefit.getMonthlyAccumulationCount() + monthlyCount);
        memberCardBenefit.setAnnuallyAccumulationCount(memberCardBenefit.getAnnuallyAccumulationCount() + annuallyCount);

        memberCardBenefitRepository.save(memberCardBenefit);
    }

    public void updateDiscountAmount(MemberCardBenefit memberCardBenefit, Long dailyAmount, Long monthlyAmount, Long annuallyAmount) {
        memberCardBenefit.setDailyDiscountAmount(memberCardBenefit.getDailyDiscountAmount() + dailyAmount);
        memberCardBenefit.setMonthlyDiscountAmount(memberCardBenefit.getMonthlyDiscountAmount() + monthlyAmount);
        memberCardBenefit.setAnnuallyDiscountAmount(memberCardBenefit.getAnnuallyDiscountAmount() + annuallyAmount);

        memberCardBenefitRepository.save(memberCardBenefit);
    }

    public void updateAccumulationAmount(MemberCardBenefit memberCardBenefit, Long dailyAmount, Long monthlyAmount, Long annuallyAmount) {
        memberCardBenefit.setDailyAccumulationAmount(memberCardBenefit.getDailyAccumulationAmount() + dailyAmount);
        memberCardBenefit.setMonthlyAccumulationAmount(memberCardBenefit.getMonthlyAccumulationAmount() + monthlyAmount);
        memberCardBenefit.setAnnuallyAccumulationAmount(memberCardBenefit.getAnnuallyAccumulationAmount() + annuallyAmount);

        memberCardBenefitRepository.save(memberCardBenefit);
    }

}
