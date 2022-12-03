import database from "../database";
import moment from "moment";
import { getCurrentKorDate } from "../utils";
import { Review } from "../@types/Review";
import UserModel from "./UserModel";
import { User } from "../@types/User";
import { getAllResolvedResult } from "../utils";
import OrderModel from "./OrderModel";
import { connect } from "http2";

type ReviewDto = {
  REVIEW_ID: number;
  USER_ID: number;
  STORE_ID: number;
  STAR_RATING: number;
  COMMENTS: string;
  CREATED_AT: string;
};

class ReviewModel {
  getReivews = async (sql: string) => {
    const conn = await database.getConnection();
    const result = (await conn.execute<ReviewDto>(sql)).rows;
    const reviewList = result?.map((review: ReviewDto) => {
      return {
        reviewId: review.REVIEW_ID,
        userId: review.USER_ID,
        storeId: review.STORE_ID,
        starRating: review.STAR_RATING,
        comments: review.COMMENTS,
        createdAt: review.CREATED_AT,
      } as Review;
    });
    const reviewListWithUser = reviewList?.map((review) => {
      return new Promise<Review>((resolve) => {
        UserModel.getUserById(review.userId!).then((user) => {
          resolve({ ...review, user } as Review);
        });
      });
    });
    return getAllResolvedResult(reviewListWithUser);
  };
  insert = async (review: Review, orderId: number) => {
    const autoIncrement = `(SELECT NVL(MAX(REVIEW_ID), 0) + 1 FROM REVIEW)`;
    const createdAt = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO REVIEW VALUES (${autoIncrement}, ${review.userId}, ${review.storeId}, ${review.starRating}, '${review.comments}', TO_DATE('${createdAt}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    const reviewIdSql = `(SELECT MAX(REVIEW_ID) FROM REVIEW)`;
    const orderMenuList = await OrderModel.getOrderMenuIdsByOrderId(orderId);
    return Promise.all(
      orderMenuList!.map((orderMenuId) => {
        return new Promise((resolve) => {
          const containsSql = `INSERT INTO CONTAINS VALUES (${orderMenuId}, ${orderId}, ${reviewIdSql})`;
          resolve(conn.execute(containsSql));
        });
      })
    );
  };
  deleteById = async (reviewId: number) => {
    const sql = `DELETE FROM REVIEW WHERE REVIEW_ID = ${reviewId}`;
    const conn = await database.getConnection();
    await conn.execute(sql);
  };
  getReviewsByUserId = async (userId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE USER_ID = ${userId}`;
    return this.getReivews(sql);
  };
  update = async (review: Review) => {
    const sql = `UPDATE REVIEW SET STAR_RATING = ${review.starRating}, COMMENTS = '${review.comments}' WHERE REVIEW_ID = ${review.reviewId}`;
    const conn = await database.getConnection();
    await conn.execute(sql);
  };
  getReviewsByStoreId = async (storeId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE STORE_ID = ${storeId}`;
    return await this.getReivews(sql);
  };
  getReviewByOrderId = async (orderId: number) => {
    const sql = `SELECT DISTINCT R.REVIEW_ID, R.USER_ID, R.STORE_ID, R.STAR_RATING, R.COMMENTS, R.CREATED_AT FROM REVIEW R, CONTAINS C WHERE R.REVIEW_ID = C.REVIEW_ID AND C.ORDER_ID = ${orderId}`;
    return await this.getReivews(sql);
  };
  getAllReview = async () => {
    const sql = `SELECT * FROM REVIEW ORDER BY CREATED_AT DESC`;
    return await this.getReivews(sql);
  };
}

export default new ReviewModel();
