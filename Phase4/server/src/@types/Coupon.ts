export type Coupon = {
  couponId?: number;
  userId?: number;
  discountAmount?: number;
  expirationDate?: string;
  minimumOrderAmount?: number;
  state?: string;
  couponEventId?: number;
};
