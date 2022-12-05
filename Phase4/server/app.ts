import express from "express";
import session from "express-session";
import cors from "cors";
import { User } from "./src/@types/User";

const corsOptions = {
  origin: true,
  credentials: true,
};

const app = express();
const port = 3010;

// Middle-ware setting
app.use(
  session({
    resave: false,
    saveUninitialized: true,
    secret: "comp322",
    cookie: {
      httpOnly: true,
      secure: false,
    },
  })
);
app.use(cors(corsOptions));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Routes
app.use("/stores", require("./src/routers/stores"));
app.use("/users", require("./src/routers/users"));
app.use("/session", require("./src/routers/session"));
app.use("/orders", require("./src/routers/orders"));
app.use("/reviews", require("./src/routers/reviews"));
app.use("/coupons", require("./src/routers/coupons"));
app.use("/coupon-event", require("./src/routers/coupon-event"));

declare module "express-session" {
  export interface SessionData {
    user?: User;
  }
}

app.listen(port, () => {
  console.log(`Server starts on ${port}`);
});
