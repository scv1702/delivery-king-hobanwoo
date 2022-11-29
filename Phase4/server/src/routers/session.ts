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
  if (result) {
    req.session.user = result;
    res.json({ success: true, message: "로그인 되었습니다." });
  } else {
    res.json({
      success: false,
      message: "아이디 또는 비밀번호가 일치하지 않습니다.",
    });
  }
});

module.exports = router;
