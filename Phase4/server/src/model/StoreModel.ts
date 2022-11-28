import database from "../database";

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
  getAllStore = async () => {
    const sql = "SELECT * FROM STORE";
    const conn = await database.getConnection();
    const result = await conn.execute<StoreDto>(sql);
    const storeList = result?.rows?.map((store: StoreDto) => {
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
  };
  getStoreById = async (storeId: number) => {
    const sql = `SELECT * FROM STORE WHERE STORE_ID = ${storeId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<StoreDto>(sql)).rows?.[0];
    if (result) {
      return {
        storeId: result.STORE_ID,
        address: result.ADDRESS,
        foodCategory: result.FOOD_CATEGORY,
        storeName: result.STORE_NAME,
        phoneNumber: result.PHONE_NUMBER,
        description: result.DESCRIPTION,
        deliveryFee: result.DELIVERY_FEE,
        image: result.IMAGE,
        businessHour: result.BUSINESS_HOUR,
      };
    } else {
      return undefined;
    }
  };
}

export default new StoreModel();
