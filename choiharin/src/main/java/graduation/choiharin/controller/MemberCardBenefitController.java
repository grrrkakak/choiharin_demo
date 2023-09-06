package graduation.choiharin.controller;

import graduation.choiharin.domain.entity.*;
import graduation.choiharin.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class MemberCardBenefitController {

    @Autowired
    private final MemberCardBenefitService memberCardBenefitService;

    /**
     * 1. 할인/적립 횟수 및 금액 업데이트
     * **/
/*    @PostMapping("/updateDiscountCount")
    public ResponseEntity<String> updateDiscountCount(@RequestBody UpdateCountRequest request) {
        MemberCardBenefit memberCardBenefit = memberCardBenefitService.findById(request.getMemberCardBenefitId());
        if (memberCardBenefit == null) {
            return ResponseEntity.badRequest().body("MemberCardBenefit not found");
        }

        memberCardBenefitService.updateDiscountCount(memberCardBenefit, request.getDailyCount(), request.getMonthlyCount(), request.getAnnuallyCount());
        return ResponseEntity.ok("Discount count updated successfully");
    }

    @PostMapping("/updateAccumulationCount")
    public ResponseEntity<String> updateAccumulationCount(@RequestBody UpdateCountRequest request) {
        MemberCardBenefit memberCardBenefit = memberCardBenefitService.findById(request.getMemberCardBenefitId());
        if (memberCardBenefit == null) {
            return ResponseEntity.badRequest().body("MemberCardBenefit not found");
        }

        memberCardBenefitService.updateAccumulationCount(memberCardBenefit, request.getDailyCount(), request.getMonthlyCount(), request.getAnnuallyCount());
        return ResponseEntity.ok("Accumulation count updated successfully");
    }

    @PostMapping("/updateDiscountAmount")
    public ResponseEntity<String> updateDiscountAmount(@RequestBody UpdateAmountRequest request) {
        MemberCardBenefit userCardBenefit = memberCardBenefitService.findById(request.getMemberCardBenefitId());
        if (userCardBenefit == null) {
            return ResponseEntity.badRequest().body("MemberCardBenefit not found");
        }

        memberCardBenefitService.updateDiscountAmount(userCardBenefit, request.getDailyAmount(), request.getMonthlyAmount(), request.getAnnuallyAmount());
        return ResponseEntity.ok("Discount amount updated successfully");
    }

    @PostMapping("/updateAccumulationAmount")
    public ResponseEntity<String> updateAccumulationAmount(@RequestBody UpdateAmountRequest request) {
        MemberCardBenefit memberCardBenefit = memberCardBenefitService.findById(request.getMemberCardBenefitId());
        if (memberCardBenefit == null) {
            return ResponseEntity.badRequest().body("MemberCardBenefit not found");
        }

        memberCardBenefitService.updateAccumulationAmount(memberCardBenefit, request.getDailyAmount(), request.getMonthlyAmount(), request.getAnnuallyAmount());
        return ResponseEntity.ok("Accumulation amount updated successfully");
    }


    // 특정 회원의 카드 혜택을 조회하는 API
    @GetMapping("/get-member-card-benefits/{memberId}")
    public ResponseEntity<List<MemberCardBenefit>> getMemberCardBenefitsByMemberId(@PathVariable Long memberId) {
        List<MemberCardBenefit> memberCardBenefits = memberCardBenefitService.getMemberCardBenefitsByMemberId(memberId);
        return new ResponseEntity<>(memberCardBenefits, HttpStatus.OK);
    }

    // 혜택의 제한을 체크하고 메시지를 반환하는 API
    @GetMapping("/check-benefit-limit/{memberCardBenefitId}")
    public ResponseEntity<String> checkBenefitLimit(@PathVariable Long memberCardBenefitId) {
        MemberCardBenefit memberCardBenefit = memberCardBenefitService.getMemberCardBenefitById(memberCardBenefitId);

        if (memberCardBenefit == null) {
            return new ResponseEntity<>("MemberCardBenefit not found", HttpStatus.NOT_FOUND);
        }

        String message = memberCardBenefitService.isBenefitWithinLimit(memberCardBenefit);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }*/

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
