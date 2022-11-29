import database from "../database";
import moment from "moment";
import { getCurrentKorDate } from "../utils";
import { Review } from "../@types/Review";

class ReviewModel {
  insert = async (review: Review) => {
    const autoIncrement = `(SELECT NVL(MAX(REVIEW_ID), 0) + 1 FROM REVIEW)`;
    const createdAt = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO REVIEW VALUES (${autoIncrement}, ${review.userId}, ${review.storeId}, ${review.starRating}, '${review.comments}', TO_DATE('${createdAt}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    return result.rows;
  };
  deleteById = async (reviewId: number) => {
    const sql = `DELETE FROM REVIEW WHERE REVIEW_ID = ${reviewId}`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    return result.rows;
  };
  getReviewsByUserId = async (userId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE USER_ID = ${userId}`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    return result.rows;
  };
  update = async (review: Review) => {
    const sql = `UPDATE REVIEW SET STAR_RATING = ${review.starRating}, COMMENTS = '${review.comments}' WHERE REVIEW_ID = ${review.reviewId}`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    return result.rows;
  };
  getReviewsByStoreId = async (storeId: number) => {
    const sql = `SELECT * FROM REVIEW WHERE STORE_ID = ${storeId}`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    return result.rows;
  };
}

export default new ReviewModel();
