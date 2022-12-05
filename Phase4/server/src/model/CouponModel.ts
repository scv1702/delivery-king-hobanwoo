import database from "../database";
import { Coupon } from "../@types/Coupon";
import { CouponEvent } from "../@types/CouponEvent";
import { getAllResolvedResult, isDBError } from "../utils";

type CouponDto = {
  COUPON_ID: number;
  USER_ID: number;
  STATE: string;
  COUPON_EVENT_ID: number;
};

type CouponEventDto = {
  COUPON_EVENT_ID: number;
  QUANTITY: number;
  DESCRIPTION: string;
  DISCOUNT_AMOUNT: number;
  EXPIRATION_DATE: string;
  MINIMUM_ORDER_AMOUNT: number;
};

class CouponModel {
  insert = async (couponEventId: number, userId: number) => {
    const conn = await database.getConnection();
    await conn.execute("SET TRANSACTION ISOLATION LEVEL SERIALIZABLE");
    const quantitySql = `SELECT * FROM COUPON_EVENT WHERE COUPON_EVENT_ID = ${couponEventId}`;
    const quantity = (await conn.execute<CouponEventDto>(quantitySql)).rows?.[0]
      .QUANTITY;
    if (quantity && quantity > 0) {
      const autoIncrement = `(SELECT NVL(MAX(COUPON_ID), 0) + 1 FROM COUPON)`;
      const couponSql = `INSERT INTO COUPON VALUES (${autoIncrement}, ${userId}, '미사용', ${couponEventId})`;
      const couponEventSql = `UPDATE COUPON_EVENT SET QUANTITY = ${
        quantity - 1
      } WHERE COUPON_EVENT_ID = ${couponEventId}`;
      try {
        await conn.execute(couponSql);
        await conn.execute(couponEventSql);
        await conn.commit();
      } catch (err) {
        console.error(err);
        if (isDBError(err)) {
          await conn.rollback();
        }
        throw err;
      }
    } else {
      throw new Error("쿠폰이 모두 소진되었습니다.");
    }
  };
  getCouponsByUserId = async (
    userId: number
  ): Promise<Coupon[] | undefined> => {
    const sql = `SELECT * FROM COUPON WHERE USER_ID = ${userId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<CouponDto>(sql)).rows;
    const coupons = result?.map((coupon: CouponDto) => {
      return {
        couponId: coupon.COUPON_ID,
        userId: coupon.USER_ID,
        state: coupon.STATE,
        couponEventId: coupon.COUPON_EVENT_ID,
      } as Coupon;
    });
    const couponsWithCouponEvent = coupons?.map((coupon) => {
      return new Promise<Coupon>((resolve) => {
        this.getCouponEventById(coupon.couponEventId!).then((couponEvent) => {
          resolve({
            ...coupon,
            discountAmount: couponEvent?.discountAmount,
            expirationDate: couponEvent?.expirationDate,
            minimumOrderAmount: couponEvent?.minimumOrderAmount,
          });
        });
      });
    });
    return getAllResolvedResult(couponsWithCouponEvent);
  };
  getCouponEventById = async (
    couponEventId: number
  ): Promise<CouponEvent | undefined> => {
    const sql = `SELECT * FROM COUPON_EVENT WHERE COUPON_EVENT_ID = ${couponEventId}`;
    const conn = await database.getConnection();
    const couponEvent = (await conn.execute<CouponEventDto>(sql)).rows?.[0];
    if (couponEvent) {
      return {
        couponEventId: couponEvent.COUPON_EVENT_ID,
        quantity: couponEvent.QUANTITY,
        description: couponEvent.DESCRIPTION,
        discountAmount: couponEvent.DISCOUNT_AMOUNT,
        expirationDate: couponEvent.EXPIRATION_DATE,
        minimumOrderAmount: couponEvent.MINIMUM_ORDER_AMOUNT,
      };
    }
  };
  getAllCopunEvent = async (): Promise<CouponEvent[] | undefined> => {
    const sql = `SELECT * FROM COUPON_EVENT`;
    const conn = await database.getConnection();
    const result = (await conn.execute<CouponEventDto>(sql)).rows;
    return result?.map((couponEvent: CouponEventDto) => {
      return {
        couponEventId: couponEvent.COUPON_EVENT_ID,
        quantity: couponEvent.QUANTITY,
        description: couponEvent.DESCRIPTION,
        discountAmount: couponEvent.DISCOUNT_AMOUNT,
        expirationDate: couponEvent.EXPIRATION_DATE,
        minimumOrderAmount: couponEvent.MINIMUM_ORDER_AMOUNT,
      };
    });
  };
}

export default new CouponModel();
