import express from "express";
import { Request, Response } from "express";
import CouponModel from "../model/CouponModel";
import { isDBError } from "../utils";

const router = express.Router();

router.post("/", async (req: Request, res: Response) => {
  if (req.session.user) {
    const couponEventId = parseInt(req.query.couponEventId as string);
    try {
      await CouponModel.insert(couponEventId, req.session.user.userId!);
      res.json({ success: true, message: "쿠폰이 발급됐습니다." });
    } catch (err) {
      if (isDBError(err)) {
        console.error(err);
        res.status(500).json({
          success: false,
          message: "일시적인 오류입니다. 다시 시도해주세요.",
        });
      } else if (err instanceof Error) {
        res.status(500).json({ success: false, message: err.message });
      }
    }
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

module.exports = router;
