package graduation.choiharin.service;

import graduation.choiharin.domain.entity.*;
import graduation.choiharin.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/*@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendationService {

    @Autowired
    private final MemberCardBenefitRepository memberCardBenefitRepository;
    @Autowired
    private final BenefitRepository benefitRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CardRepository cardRepository;

    // 수정해야함
    public String getRecommendedCard(Long userId, String category) {
        Member member = memberRepository.findById(userId).orElse(null);
        List<Card> userCards = member.getCards();

        Card recommendedCard = null;
        double maxDiscountRate = 0.0;

        for (Card card : userCards) {
            Benefit benefit = card.getBenefitByCategory(category);
            if (benefit != null) {
                double discountRate = benefit.getDiscountRate();
                if (discountRate > maxDiscountRate) {
                    maxDiscountRate = discountRate;
                    recommendedCard = card;
                }
            }
        }

        return (recommendedCard != null) ? recommendedCard.getName() : "No suitable card found.";
    }
}*/
