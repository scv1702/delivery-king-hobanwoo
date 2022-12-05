import express from "express";
import { Request, Response } from "express";
import CouponModel from "../model/CouponModel";

const router = express.Router();

router.get("/", async (req: Request, res: Response) => {
  res.json(await CouponModel.getAllCopunEvent());
});

module.exports = router;
