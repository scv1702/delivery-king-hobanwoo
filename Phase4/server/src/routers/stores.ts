import express from "express";
import { Request, Response } from "express";
import MenuModel from "../model/MenuModel";
import StoreModel from "../model/StoreModel";
import ReviewModel from "../model/ReviewModel";

const router = express.Router();

router.get("/", async (req: Request, res: Response) => {
  if (req.query.address) {
    res.json(await StoreModel.getStoresByAddress(req.query.address as string));
  } else if (req.query.category) {
    res.json(
      await StoreModel.getStoresByFoodCategory(req.query.category as string)
    );
  } else if (req.query.department) {
    res.json(
      await StoreModel.getStoresByDepartment(req.query.department as string)
    );
  } else {
    res.json(await StoreModel.getAllStore());
  }
});

router.get("/:id", async (req, res) => {
  const storeId = parseInt(req.params.id);
  const result = await StoreModel.getStoreById(storeId);
  res.json(result);
});

router.get("/:id/menu", async (req, res) => {
  const storeId = parseInt(req.params.id);
  const result = await MenuModel.getMenuListByStoreId(storeId);
  res.json(result);
});

router.get("/:id/reviews", async (req: Request, res: Response) => {
  const storeId = parseInt(req.params.id);
  const reviews = await ReviewModel.getReviewsByStoreId(storeId);
  res.json(reviews);
});

module.exports = router;
