import database from "../database";
import { User } from "../@types/User";
import { isDBError } from "../utils";

type UserDto = {
  USER_ID: number;
  USER_NAME: string;
  DNAME: string;
  PASSWORD: string;
  PHONE_NUMBER: string;
  MEMBERSHIP_TIER: string;
};

class UserModel {
  async insert(user: User) {
    const { username, dname, password, phoneNumber } = user;
    const autoIncrement = `(SELECT NVL(MAX(USER_ID), 0) + 1 FROM USERS)`;
    const sql = `INSERT INTO USERS VALUES (${autoIncrement}, '${username}', '${dname}', '${password}', '${phoneNumber}', '고마워요')`;
    const conn = await database.getConnection();
    try {
      await conn.execute(sql);
    } catch (err) {
      if (isDBError(err)) {
        if (err.errorNum === 1) {
          console.error(err);
          throw new Error("사용할 수 없는 아이디입니다.");
        } else {
          console.error(err);
          throw new Error(
            "회원 가입 도중 오류가 발생했습니다. 다시 시도해주세요"
          );
        }
      }
    }
  }
  async login(username: string, password: string): Promise<User | undefined> {
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
  async getUsers(): Promise<Array<User> | undefined> {
    const sql = "SELECT * FROM USERS";
    const conn = await database.getConnection();
    const result = (await conn.execute<User>(sql)).rows;
    if (result) {
      return result;
    }
    return undefined;
  }
  getMembershipTier() {}
  updateMembershipTier() {}
  getAddress = async (userId: number) => {
    type AddressDto = {
      UADDRESS: string;
    };
    const sql = `SELECT * FROM USER_ADDRESS WHERE USER_ID = ${userId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<AddressDto>(sql)).rows;
    return result?.map((address: AddressDto) => {
      return {
        address: address.UADDRESS,
      };
    });
  };
  insertAddress = async (userId: number, address: string) => {
    const sql = `INSERT INTO USER_ADDRESS VALUES (${userId}, '${address}')`;
    const conn = await database.getConnection();
    await conn.execute(sql);
  };
  getUserById = async (userId: number): Promise<User | undefined> => {
    const sql = `SELECT * FROM USERS WHERE USER_ID = ${userId}`;
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
  };
}

export default new UserModel();
