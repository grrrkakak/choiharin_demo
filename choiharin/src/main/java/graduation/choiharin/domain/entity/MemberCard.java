package graduation.choiharin.domain.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.time.*;

@Entity
@Getter @Setter
@Table(name = "member_card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberCard extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Card card;

    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Override
    public String toString() {
        return "MemberCard{" +
                "id=" + id +
                ", member=" + member +
                ", card=" + card +
                ", expirationDate=" + expirationDate +
                ", status=" + status +
                '}';
    }
}
