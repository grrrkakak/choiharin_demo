package graduation.choiharin.service;

import graduation.choiharin.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCardService {

    @Autowired
    private final MemberCardRepository memberCardRepository;
}
