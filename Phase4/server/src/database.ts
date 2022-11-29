import OracleDB from "oracledb";
import { OUT_FORMAT_OBJECT } from "oracledb";

class Database {
  private oracleDB = OracleDB;
  private config = {
    user: `delivery_king_hobanwoo`,
    password: `comp322`,
    connectString: "localhost/xe",
  };
  private connection: Promise<OracleDB.Connection>;
  public constructor() {
    this.oracleDB.autoCommit = true;
    this.oracleDB.outFormat = OUT_FORMAT_OBJECT;
    this.connection = this.oracleDB.getConnection(this.config);
  }
  public async getConnection() {
    return await this.connection;
  }
}

const database = new Database();
export default database;
