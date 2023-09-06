package graduation.choiharin.domain.entity;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum KakaoCategory {

    ALL("ALL", "전체"), MT1("MT1", "대형마트"),
    CS2("CS2","편의점"), AC5("AC5", "학원"),
    OL7("OL7", "주유소, 충전소"), CT1("CT1", "문화시설"),
    AG2("AG2", "중개업소"), AD5("AD5", "숙박업소"),
    FD6("FD6", "음식점"), CE7("CE7", "카페"),
    HP8("HP8", "병원"), PM9("PM9", "약국");
    private final String code;
    private final String description;
}
