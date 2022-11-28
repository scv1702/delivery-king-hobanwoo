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
  getAllStore = async (): Promise<Array<Store> | undefined> => {
    const sql = "SELECT * FROM STORE";
    const conn = await database.getConnection();
    const result = (await conn.execute<StoreDto>(sql)).rows;
    if (result) {
      const storeList = result.map((store: StoreDto) => {
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
      return storeList;
    }
    return undefined;
  };
  getStoreById = async (storeId: number): Promise<Store | undefined> => {
    const sql = `SELECT * FROM STORE WHERE STORE_ID = ${storeId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<StoreDto>(sql)).rows;
    if (result) {
      const {
        STORE_ID,
        ADDRESS,
        FOOD_CATEGORY,
        STORE_NAME,
        PHONE_NUMBER,
        DESCRIPTION,
        DELIVERY_FEE,
        IMAGE,
        BUSINESS_HOUR,
      } = result[0];
      return {
        storeId: STORE_ID,
        address: ADDRESS,
        foodCategory: FOOD_CATEGORY,
        storeName: STORE_NAME,
        phoneNumber: PHONE_NUMBER,
        description: DESCRIPTION,
        deliveryFee: DELIVERY_FEE,
        image: IMAGE,
        businessHour: BUSINESS_HOUR,
      };
    }
    return undefined;
  };
}

export default new StoreModel();
