package graduation.choiharin.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String membername;

    @Column(nullable = false, unique = true)
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    //jwt 인증용
    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))})
    private Set<Authority> authorities;

    /**
     * jwt 발급 관련하여 일부러 getUsername() 메소드가 email을 반환하도록 작성함
     * **/
    public String getUsername() {
        return email;
    }

    /**
     * api 설계에 따라 이거 필요 없이 MemberCard로 모두 해결할 수 있을 듯. 필요할 때 주석 해제 후 사용하기
     */
//    @JsonIgnore
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<MemberCard> memberCards = new ArrayList<>();

    /**
     * api 설계에 따라 이거 필요 없이 MemberCardBenefit으로 모두 해결할 수 있을 듯. 필요할 때 주석 해제 후 사용하기
     */
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<MemberCardBenefit> memberCardBenefits = new ArrayList<>();

}
