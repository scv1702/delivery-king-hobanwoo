import express from "express";
import { Request, Response } from "express";
import ReviewModel from "../model/ReviewModel";
import OrderModel from "../model/OrderModel";

const router = express.Router();

router.post("/", async (req: Request, res: Response) => {
  if (req.session.user) {
    const userId = req.session.user.userId;
    const orderId = parseInt(req.query.orderId as string);
    const { starRating, comments } = req.body;
    const order = await OrderModel.getOrderById(orderId);
    await ReviewModel.insert({
      userId,
      storeId: order?.storeId,
      starRating,
      comments,
    });
    res.json({ success: true, message: "리뷰가 등록되었습니다." });
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

router.delete("/:reviewId", async (req, res) => {
  if (req.session.user) {
    const reviewId = parseInt(req.params.reviewId);
    await ReviewModel.deleteById(reviewId);
    res.json({ success: true, message: "리뷰가 삭제되었습니다." });
  } else {
    res.json({ success: false });
  }
});

module.exports = router;
