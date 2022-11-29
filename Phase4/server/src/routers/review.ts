const express = require("express");
const router = express.Router();

const reviewModel = require("../model/ReviewModel");
const ordersModel = require("../models/OrdersModel");

router.post("/:orderId", async (req, res) => {
  if (req.session.user) {
    const userId = req.session.user.userId;
    const { orderId } = req.params;
    const { starRating, comments } = req.body;
    const order = await ordersModel.getOrderById(orderId);
    const review = await reviewModel.insert({
      userId,
      storeId: order.storeId,
      starRating,
      comments,
    });
    res.json(review);
  }
});

router.get("/:storeId", async (req, res) => {
  const { storeId } = req.params;
  const reviews = await reviewModel.getReviewsByStoreId(storeId);
  res.json(reviews);
});

router.put("/:reviewId", async (req, res) => {
  if (req.session.user) {
    const { reviewId } = req.params;
    const { starRating, comments } = req.body;
    const review = await reviewModel.update({
      reviewId,
      starRating,
      comments,
    });
    res.json(review);
  }
});

router.delete("/:reviewId", async (req, res) => {
  if (req.session.user) {
    const { reviewId } = req.params;
    const result = await reviewModel.delete(reviewId);
    res.json(result);
  } else {
    res.json({ success: false });
  }
});
