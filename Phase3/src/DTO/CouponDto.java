package DTO;

public class CouponDto {
    public int couponId;
    public int userId;
    public int discountAmount;
    public String expirationDate;
    public int minimumOrderAmount;
    public String state;

    public CouponDto (int couponId, int userId, int discountAmount, String expirationDate, int minimumOrderAmount, String state) {
        this.couponId = couponId;
        this.userId = userId;
        this.discountAmount = discountAmount;
        this.expirationDate = expirationDate;
        this.minimumOrderAmount = minimumOrderAmount;
        this.state = state;
    }
}