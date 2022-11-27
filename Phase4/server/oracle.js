const oracledb = require("oracledb");

oracledb.autoCommit = true;
oracledb.initOracleClient({ configDir: `${process.env.ORACLE_HOME}/lib` });

const user = "delivery_king_hobanwoo";
const password = "comp322";
const connectString = "localhost/xe";

module.exports = {
  executeQuery: async (sql) => {
    try {
      const connection = await oracledb.getConnection({
        user,
        password,
        connectString,
      });
      const result = await connection.execute(sql);
      await connection.close(); // Always close connections
      return result;
    } catch (err) {
      console.error("SQL Execute Error: " + err);
    }
  },
};
