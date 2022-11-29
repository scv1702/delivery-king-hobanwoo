import database from "../database";
import { Store } from "../@types/Store";

type StoreDto = {
  STORE_ID: number;
  ADDRESS: string;
  FOOD_CATEGORY: string;
  STORE_NAME: string;
  PHONE_NUMBER: string;
  DESCRIPTION: string;
  DELIVERY_FEE: number;
  IMAGE: string;
  BUSINESS_HOUR: number;
};

class StoreModel {
  getStores = async (sql: string): Promise<Store[] | undefined> => {
    const conn = await database.getConnection();
    const result = (await conn.execute<StoreDto>(sql)).rows;
    return result?.map((store: StoreDto) => {
      return {
        storeId: store.STORE_ID,
        address: store.ADDRESS,
        foodCategory: store.FOOD_CATEGORY,
        storeName: store.STORE_NAME,
        phoneNumber: store.PHONE_NUMBER,
        description: store.DESCRIPTION,
        deliveryFee: store.DELIVERY_FEE,
        image: store.IMAGE,
        businessHour: store.BUSINESS_HOUR,
      };
    });
  };
  getAllStore = async (): Promise<Store[] | undefined> => {
    const sql = "SELECT * FROM STORE";
    return this.getStores(sql);
  };
  getStoreById = async (storeId: number): Promise<Store | undefined> => {
    const sql = `SELECT * FROM STORE WHERE STORE_ID = ${storeId}`;
    const result = await this.getStores(sql);
    return result?.[0];
  };
  getStoresByAddress = async (
    address: string
  ): Promise<Store[] | undefined> => {
    const sql = `SELECT * FROM STORE WHERE ADDRESS = '${address}'`;
    return this.getStores(sql);
  };
  getStoresByFoodCategory = async (
    foodCategory: string
  ): Promise<Store[] | undefined> => {
    const sql = `SELECT * FROM STORE WHERE FOOD_CATEGORY = '${foodCategory}'`;
    return this.getStores(sql);
  };
  getStoresByDepartment = async (
    department: string
  ): Promise<Store[] | undefined> => {
    const sql = `SELECT * FROM STORE WHERE ADDRESS LIKE '%${department}%'`;
    return this.getStores(sql);
  };
}

export default new StoreModel();
