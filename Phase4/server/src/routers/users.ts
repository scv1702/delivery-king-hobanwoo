import express from "express";
import { Request, Response } from "express";
import ReviewModel from "../model/ReviewModel";
import UserModel from "../model/UserModel";
import CouponModel from "../model/CouponModel";
import OrderModel from "../model/OrderModel";

const router = express.Router();

router.post("/", async (req: Request, res: Response) => {
  const { username, dname, password, phoneNumber } = req.body;
  await UserModel.insert({
    username,
    dname,
    password,
    phoneNumber,
  });
  res.json({ success: true, message: "회원 가입 되었습니다." });
});

router.get("/", async (req, res) => {
  const result = await UserModel.getUsers();
  res.json(result);
});

router.get("/address", async (req, res) => {
  if (req.session.user) {
    res.json(await UserModel.getAddress(req.session.user.userId!));
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.post("/address", async (req, res) => {
  if (req.session.user) {
    const { address } = req.body;
    await UserModel.insertAddress(req.session.user.userId!, address);
    res.json({ success: true, message: "주소가 등록되었습니다." });
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.get("/coupon", async (req, res) => {
  if (req.session.user) {
    res.json(await CouponModel.getCouponByUserId(req.session.user.userId!));
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.get("/orders", async (req, res) => {
  if (req.session.user) {
    res.json(await OrderModel.getOrdersByUserId(req.session.user.userId!));
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

router.get("/reivew", async (req, res) => {
  if (req.session.user) {
    res.json(await ReviewModel.getReviewsByUserId(req.session.user.userId!));
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

module.exports = router;
