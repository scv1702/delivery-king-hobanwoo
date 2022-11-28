import express from "express";
import { Request, Response } from "express";
import UserModel from "../model/UserModel";

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

// router.get("/address", async (req, res) => {
//   if (req.session.user) {
//     const sql = `SELECT ADDRESS FROM USERS WHERE ${req.session.user.userId} = USER_ID`;
//     const result = await db.executeQuery(sql);
//     res.json(result.rows);
//   } else {
//     res.json({ success: false });
//   }
// });

// router.post("/address", async (req, res) => {
//   if (req.session.user) {
//     const { address } = req.body;
//     const sql = `INSERT INTO USER_ADDRESS VALUES (${req.session.user.userId}, '${address}')`;
//     const result = await db.executeQuery(sql);
//     res.json(result);
//   } else {
//     res.json({ success: false });
//   }
// });

// router.get("/coupon", async (req, res) => {
//   if (req.session.user) {
//     const sql = `SELECT * FROM COUPON WHERE ${req.session.user.userId} = USER_ID`;
//     const result = await db.executeQuery(sql);
//     res.json(result.rows);
//   } else {
//     res.json({ success: false });
//   }
// });

// router.get("/orders", async (req, res) => {
//   if (req.session.user) {
//     const sql = `SELECT * FROM ORDERS WHERE USER_ID = ${req.session.user.userId}`;
//     const result = await db.executeQuery(sql);
//     res.json(result.rows);
//   } else {
//     res.json({ success: false });
//   }
// });

// router.get("/reivew", async (req, res) => {
//   if (req.session.user) {
//     const result = await reviewModel.getReviewsByUserId(
//       req.session.user.userId
//     );
//     res.json(result);
//   } else {
//     res.json({ success: false });
//   }
// });

module.exports = router;
