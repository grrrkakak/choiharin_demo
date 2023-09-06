package graduation.choiharin.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.*;

@Entity
@Getter @Setter
@Table(name = "member_card_benefit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCardBenefit extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "benefit_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Benefit benefit;

    //할인 횟수 카운팅
    private int dailyDiscountCount;
    private int monthlyDiscountCount;
    private int annuallyDiscountCount;
    //적립 횟수 카운팅
    private int dailyAccumulationCount;
    private int monthlyAccumulationCount;
    private int annuallyAccumulationCount;
    //할인 금액 집계
    private Long dailyDiscountAmount;
    private Long monthlyDiscountAmount;
    private Long annuallyDiscountAmount;
    //적립 금액 집계
    private Long dailyAccumulationAmount;
    private Long monthlyAccumulationAmount;
    private Long annuallyAccumulationAmount;

    //reset date
    private LocalDateTime lastResetDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
}
