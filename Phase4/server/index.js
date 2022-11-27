const express = require("express");
const session = require("express-session");
const cors = require("cors");

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
app.use("/stores", require("./routers/stores"));

app.listen(port, () => {
  console.log(`express is running on ${port}`);
});
