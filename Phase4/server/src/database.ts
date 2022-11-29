import OracleDB from "oracledb";
import { OUT_FORMAT_OBJECT } from "oracledb";

class Database {
  private oracleDB = OracleDB;
  private config = {
    user: `delivery_king_hobanwoo`,
    password: `comp322`,
    connectString: "localhost/xe",
  };
  public constructor() {
    this.oracleDB.autoCommit = true;
    this.oracleDB.outFormat = OUT_FORMAT_OBJECT;
  }
  public async getConnection() {
    return await this.oracleDB.getConnection(this.config);
  }
}

const database = new Database();
export default database;
