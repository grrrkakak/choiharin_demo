package graduation.choiharin.controller;

import graduation.choiharin.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberCardController {

    @Autowired
    private final MemberCardService memberCardService;

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
