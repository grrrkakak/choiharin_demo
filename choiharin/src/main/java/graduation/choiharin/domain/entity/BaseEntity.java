package graduation.choiharin.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Getter
@Setter
@MappedSuperclass //속성을 상속해주는 진짜 상속은 아님
public class BaseEntity {

    @Column(updatable = false) //혹시 실수로 값을 바꿔도 update되지 않음
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
