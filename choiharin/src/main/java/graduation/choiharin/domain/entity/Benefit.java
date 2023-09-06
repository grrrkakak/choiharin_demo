package graduation.choiharin.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@DynamicInsert
//@DynamicUpdate
public class Benefit extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Card card;

    @Enumerated(EnumType.STRING)
    private GoogleCategory googleCategory;

    @Enumerated(EnumType.STRING)
    private KakaoCategory kakaoCategory;

    private String franchise;

    //적립률, 고정 적립금액
    private double accumulationRate;
    private double accumulationAmount;
    //할인율, 고정 할인금액
    private double discountRate;
    private double discountAmount;
    //혜택 횟수 한도
    private int dailyCountLimit;
    private int monthlyCountLimit;
    private int annuallyCountLimit;
    //혜택 금액 한도
    private Long perPayAmountLimit;
    private Long dailyAmountLimit;
    private Long monthlyAmountLimit;
    private Long annuallyAmountLimit;
}
