import database from "../database";
import { User } from "../@types/User";
import { DBError } from "oracledb";

type UserDto = {
  USER_ID: number;
  USER_NAME: string;
  DNAME: string;
  PASSWORD: string;
  PHONE_NUMBER: string;
  MEMBERSHIP_TIER: string;
};

function isDBError(err: any): err is DBError {
  return "errorNum" in err && "message" in err && "offset" in err;
}

class UserModel {
  async insert(user: User) {
    const { username, dname, password, phoneNumber } = user;
    const autoIncrement = `(SELECT NVL(MAX(USER_ID), 0) + 1 FROM USERS)`;
    const sql = `INSERT INTO USERS VALUES (${autoIncrement}, '${username}', '${dname}', '${password}', '${phoneNumber}', '고마워요')`;
    const conn = await database.getConnection();
    try {
      await conn.execute(sql);
      return true;
    } catch (err) {
      if (isDBError(err)) {
        console.error(err.message);
      }
    }
  }
  async login(username: string, password: string) {
    const sql = `SELECT * FROM USERS WHERE USER_NAME = '${username}' AND PASSWORD = '${password}'`;
    const conn = await database.getConnection();
    const result = (await conn.execute<UserDto>(sql)).rows?.[0];
    if (result) {
      return {
        userId: result.USER_ID,
        username: result.USER_NAME,
        dname: result.DNAME,
        password: result.PASSWORD,
        phoneNumber: result.PHONE_NUMBER,
        membershipTier: result.MEMBERSHIP_TIER,
      };
    } else {
      return undefined;
    }
  }
  async getUsers(): Promise<Array<User>> {
    const sql = "SELECT * FROM USERS";
    const conn = await database.getConnection();
    const result = (await conn.execute<User>(sql)).rows!;
    return result;
  }
  getMembershipTier() {}
  updateMembershipTier() {}
}

export default new UserModel();
