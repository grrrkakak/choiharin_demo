package graduation.choiharin.repository;

import graduation.choiharin.domain.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardname(String cardname);
}
