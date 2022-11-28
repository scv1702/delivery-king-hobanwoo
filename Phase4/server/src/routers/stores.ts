import express from "express";
import { Request, Response } from "express";
import MenuModel from "../model/MenuModel";
import StoreModel from "../model/StoreModel";

const router = express.Router();

router.get("/", async (req: Request, res: Response) => {
  // const sql = "SELECT * FROM STORE ";
  // if (req.query.address) {
  //   sql.concat(`WHERE ADDRESS LIKE ${req.query.address} `);
  // }
  // if (req.query.category) {
  //   if (sql.includes("WHERE")) {
  //     sql.concat(`AND FOOD_CATEGORY = ${req.query.category}`);
  //   } else {
  //     sql.concat(`WHERE FOOD_CATEGORY = ${req.query.category}`);
  //   }
  // }
  // if (req.query.department) {
  //   sql.concat(
  //     "S, DEPARTMENT D, COOPERATES C " +
  //       `WHERE D.DNAME = ${req.query.department} ` +
  //       "AND C.DNAME = D.DNAME " +
  //       "AND S.STORE_ID = C.STORE_ID"
  //   );
  // }
  // const result = await db.executeQuery(sql);
  const result = await StoreModel.getAllStore();
  res.json(result);
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

module.exports = router;
