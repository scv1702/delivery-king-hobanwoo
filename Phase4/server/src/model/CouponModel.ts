import database from "../database";
import { Coupon } from "../@types/Coupon";

type CouponDto = {
  COUPON_ID: number;
  USER_ID: number;
  DISCOUNT_AMOUNT: number;
  EXPIRATION_DATE: string;
  MINIMUM_ORDER_AMOUNT: number;
  STATE: string;
};

class CouponModel {
  getCouponByUserId = async (userId: number): Promise<Coupon[] | undefined> => {
    const sql = `SELECT * FROM COUPON WHERE USER_ID = ${userId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<CouponDto>(sql)).rows;
    return result?.map((coupon: CouponDto) => {
      return {
        couponId: coupon.COUPON_ID,
        userId: coupon.USER_ID,
        discountAmount: coupon.DISCOUNT_AMOUNT,
        expirationDate: coupon.EXPIRATION_DATE,
        minimumOrderAmount: coupon.MINIMUM_ORDER_AMOUNT,
        state: coupon.STATE,
      };
    });
  };
}

export default new CouponModel();
