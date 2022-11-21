package View;

import DTO.CouponDto;

import java.util.ArrayList;

public class CouponView {
    public void showMyCoupons(ArrayList<CouponDto> couponList) {
        for (CouponDto coupon : couponList) {
            String output = String.format(
                    "쿠폰 ID: %d\n" + "할인율: %d%%\n" + "만료일: %s\n" + "최소주문금액: %d\n" + "상태: %s\n\n",
                    coupon.couponId, coupon.discountAmount, coupon.expirationDate, coupon.minimumOrderAmount, coupon.state);
            System.out.println(output);
        }
    }

    public void noMyCoupons() {
        System.out.println("보유한 쿠폰이 없습니다.\n");
    }
}
