package View;

import DTO.CouponDto;

import java.util.ArrayList;

public class CouponView {
    public void showMyCouponsStart() {
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                "│       \t\t\t 내 쿠폰 목록 조회 \t\t\t\t            │");
    }

    public void showMyCoupons(ArrayList<CouponDto> couponDto) {
        for (int i = 0; i < couponDto.size(); i++) {
            String output = String.format(
                    "│----┬----------------------------------------------------------│\n" +
                    "│    │  쿠폰ID : %d\n" +
                    "│    │  할인율 : %d%%\n" +
                    "│    │  만료일 : %s\n" +
                    "│    │  최소주문금액 : %d\n" +
                    "│    │  상태 : %s\n" +
                    "│----┴----------------------------------------------------------│"
                    , couponDto.get(i).couponId, couponDto.get(i).discountAmount, couponDto.get(i).expirationDate, couponDto.get(i).minimumOrderAmount, couponDto.get(i).state);
            System.out.println(output);
        }
    }
    public void showMyCouponsEnd() {
        System.out.println("└---------------------------------------------------------------┘\n");
    }
}
