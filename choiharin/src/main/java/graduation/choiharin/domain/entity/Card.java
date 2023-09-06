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
public class Card extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private String cardname;

    private String company;

    private String imageUrl;

    //양방향 연관관계 굳이 필요 없으면 안 넣어도 된다. 필요할 때 추가하기
//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
//    private List<UserCard> userCards = new ArrayList<>();

    //마찬가지로 필요할 때 추가하기
//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
//    private List<Benefit> benefits = new ArrayList<>();

}
