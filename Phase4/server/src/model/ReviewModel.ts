import database from "../database";
import moment from "moment";
import { getCurrentKorDate } from "../utils";
import { Review } from "../@types/Review";

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
    conn.close();
    return result?.map((review: ReviewDto) => {
      return {
        reviewId: review.REVIEW_ID,
        userId: review.USER_ID,
        storeId: review.STORE_ID,
        starRating: review.STAR_RATING,
        comments: review.COMMENTS,
        createdAt: review.CREATED_AT,
      } as Review;
    });
  };
  insert = async (review: Review) => {
    const autoIncrement = `(SELECT NVL(MAX(REVIEW_ID), 0) + 1 FROM REVIEW)`;
    const createdAt = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO REVIEW VALUES (${autoIncrement}, ${review.userId}, ${review.storeId}, ${review.starRating}, '${review.comments}', TO_DATE('${createdAt}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    conn.close();
  };
  deleteById = async (reviewId: number) => {
    const sql = `DELETE FROM REVIEW WHERE REVIEW_ID = ${reviewId}`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    conn.close();
  };
  getReviewsByUserId = async (userId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE USER_ID = ${userId}`;
    return this.getReivews(sql);
  };
  update = async (review: Review) => {
    const sql = `UPDATE REVIEW SET STAR_RATING = ${review.starRating}, COMMENTS = '${review.comments}' WHERE REVIEW_ID = ${review.reviewId}`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    conn.close();
  };
  getReviewsByStoreId = async (storeId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE STORE_ID = ${storeId}`;
    return await this.getReivews(sql);
  };
}

export default new ReviewModel();
