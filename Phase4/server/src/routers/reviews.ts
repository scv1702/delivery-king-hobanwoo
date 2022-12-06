import express from "express";
import { Request, Response } from "express";
import ReviewModel from "../model/ReviewModel";
import OrderModel from "../model/OrderModel";
import { getAllResolvedResult } from "../utils";
import { Review } from "../@types/Review";
import StoreModel from "../model/StoreModel";

const router = express.Router();

router.delete("/:reviewId", async (req, res) => {
  if (req.session.user) {
    const reviewId = parseInt(req.params.reviewId);
    await ReviewModel.deleteById(reviewId);
    res.json({ success: true, message: "리뷰가 삭제되었습니다." });
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.put("/:reviewId", async (req, res) => {
  if (req.session.user) {
    const reviewId = parseInt(req.params.reviewId);
    const { starRating, comments } = req.body;
    await ReviewModel.update({
      reviewId,
      starRating,
      comments,
    });
    res.json({ success: true, message: "리뷰가 수정되었습니다." });
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.get("/", async (req: Request, res: Response) => {
  const reviews = await ReviewModel.getAllReview();
  res.json(reviews);
});

router.post("/", async (req: Request, res: Response) => {
  if (req.session.user) {
    const userId = req.session.user.userId;
    const orderId = parseInt(req.query.orderId as string);
    const { starRating, comments } = req.body;
    const order = await OrderModel.getOrderById(orderId);
    try {
      await ReviewModel.insert(
        {
          userId,
          storeId: order?.storeId,
          starRating,
          comments,
        },
        orderId
      );
      res.json({ success: true, message: "리뷰가 등록되었습니다." });
    } catch (err) {
      if (err instanceof Error) {
        res.status(500).json({ success: false, message: err.message });
      }
    }
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

module.exports = router;
