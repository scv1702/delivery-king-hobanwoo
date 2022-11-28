import express from "express";
import { Request, Response } from "express";
import UserModel from "../model/UserModel";
import { User } from "../@types/User";

const router = express.Router();

declare module "express-session" {
  export interface SessionData {
    user?: User;
  }
}

router.post("/", async (req: Request, res: Response) => {
  const { username, password } = req.body;
  const result = await UserModel.login(username, password);
  if (result === undefined) {
    res.json({ success: false });
  }
  req.session.user = result!;
  res.json({ success: true });
});

module.exports = router;
