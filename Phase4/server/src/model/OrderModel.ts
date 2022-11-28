import moment from "moment";
import database from "../database";
import { getCurrentKorDate } from "../utils";
import MenuModel from "./MenuModel";
import { Order } from "../@types/Order";

type OrderDto = {
  ORDER_ID: number;
  USER_ID: number;
  STORE_ID: number;
  STORE_NAME: string;
  PAYMENT: string;
  STATE: string;
  ORDER_DATE: string;
};

class OrderModel {
  getOrderById = async (orderId: number) => {
    const sql = `SELECT * FROM ORDERS WHERE ${orderId} = ORDER_ID`;
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderDto>(sql))?.rows?.[0];
    if (result) {
      const { ORDER_ID, USER_ID, STORE_ID, PAYMENT, STATE, ORDER_DATE } =
        result;
      return {
        orderId: ORDER_ID,
        userId: USER_ID,
        storeId: STORE_ID,
        payment: PAYMENT,
        state: STATE,
        orderDate: ORDER_DATE,
      };
    }
    return undefined;
  };
  getMaxOrderId = async () => {
    type MaxOrderIdDto = {
      MAX_ORDER_ID: number;
    };
    const sql = `SELECT MAX(ORDER_ID) AS MAX_ORDER_ID FROM ORDERS`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MaxOrderIdDto>(sql))?.rows?.[0]
      ?.MAX_ORDER_ID!;
    return result;
  };
  insert = async (order: Order) => {
    const { userId, storeId, orderMenuList, payment } = order;
    const orderId = (await this.getMaxOrderId()) + 1;
    const orderDate = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO ORDERS VALUES (${orderId}, ${userId}, ${storeId}, '${payment}', '주문 중', to_date('${orderDate}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    const result = await conn.execute(sql);
    await Promise.all(
      orderMenuList.map((orderMenu, index) => {
        return new Promise((resolve, reject) => {
          MenuModel.getMenuByMenuName(orderMenu.menuName).then((menu) => {
            const { image, price } = menu!;
            const sql = `INSERT INTO ORDER_MENU VALUES (${
              index + 1
            }, ${orderId}, '${orderMenu.menuName}', '${image}', ${price}, ${
              orderMenu.quantity
            })`;
            resolve(conn.execute(sql));
          });
        });
      })
    );
    console.log(result);
    return result;
  };
}

export default new OrderModel();
