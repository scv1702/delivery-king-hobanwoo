import express from "express";
import session from "express-session";
import cors from "cors";

const corsOptions = {
  origin: true,
  credentials: true,
};

const app = express();
const port = 3000;

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

app.listen(port, () => {
  console.log(`Server starts on ${port}`);
});
