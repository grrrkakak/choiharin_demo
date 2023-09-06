package graduation.choiharin.repository;

import graduation.choiharin.domain.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {
    List<Benefit> findByGoogleCategory(GoogleCategory googleCategory);

}
