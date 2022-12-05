import express from "express";
import { Request, Response } from "express";
import OrderModel from "../model/OrderModel";

const router = express.Router();

router.post("/", async (req: Request, res: Response) => {
  if (req.session.user) {
    const { storeId, orderMenuList, payment } = req.body;
    await OrderModel.insert({
      userId: req.session.user.userId!,
      storeId,
      orderMenuList,
      payment,
    });
    res.json({ success: true, message: "주문 되었습니다." });
  } else {
    res.status(401).json({ success: false, message: "로그인이 필요합니다." });
  }
});

module.exports = router;
