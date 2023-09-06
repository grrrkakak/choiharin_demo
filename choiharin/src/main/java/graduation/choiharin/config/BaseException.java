package graduation.choiharin.config;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;  //BaseResoinseStatus 객체에 매핑
}