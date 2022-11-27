const express = require("express");
const router = express.Router();

const db = require("../oracle");

router.get("/", async (req, res) => {
  const sql = null;
  if (req.query.address) {
    sql = `SELECT * FROM STORE WHERE ADDRESS LIKE ${req.query.address}`;
  }
  if (req.query.category) {
    sql = `SELECT * FROM STORE WHERE FOOD_CATEGORY = ${category}`;
  }
  const result = await db.executeQuery(sql);
  res.json(result.rows);
});

router.get("/:id", async (req, res) => {
  const sql = `SELECT * FROM STORE WHERE STORE_ID = ${req.params.id}`;
  const result = await db.executeQuery(sql);
  res.json(result.rows);
});

router.get("/:id/menu", async (req, res) => {
  const sql = `SELECT * FROM MENU WHERE STORE_ID = ${req.params.id}`;
  const result = await db.executeQuery(sql);
  res.json(result.rows);
});

module.exports = router;
