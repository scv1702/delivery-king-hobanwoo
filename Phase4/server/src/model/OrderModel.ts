import moment from "moment";
import database from "../database";
import { getCurrentKorDate } from "../utils";
import MenuModel from "./MenuModel";
import { Order } from "../@types/Order";
import { OrderMenu } from "../@types/OrderMenu";

type OrderDto = {
  ORDER_ID: number;
  USER_ID: number;
  STORE_ID: number;
  STORE_NAME: string;
  PAYMENT: string;
  STATE: string;
  ORDER_DATE: string;
};

type OrderMenuDto = {
  ORDER_MENU_ID: number;
  ORDER_ID: number;
  MENU_NAME: string;
  MENU_IMAGE: string;
  MENU_PRICE: number;
  QUANTITY: number;
};

class OrderModel {
  getOrderMenuById = async (
    orderId: number
  ): Promise<Array<OrderMenu> | undefined> => {
    const sql = `SELECT * FROM ORDER_MENU WHERE ORDER_ID = ${orderId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderMenuDto>(sql)).rows;
    if (result) {
      const orderMenuList = result.map((orderMenu: OrderMenuDto) => {
        return {
          orderMenuId: orderMenu.ORDER_MENU_ID,
          orderId: orderMenu.ORDER_ID,
          menuName: orderMenu.MENU_NAME,
          image: orderMenu.MENU_IMAGE,
          price: orderMenu.MENU_PRICE,
          quantity: orderMenu.QUANTITY,
        };
      });
      return orderMenuList;
    }
    return undefined;
  };
  getOrderById = async (orderId: number): Promise<Order | undefined> => {
    const sql = `SELECT * FROM ORDERS WHERE ${orderId} = ORDER_ID`;
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderDto>(sql)).rows;
    if (result) {
      const { ORDER_ID, USER_ID, STORE_ID, PAYMENT, STATE, ORDER_DATE } =
        result[0];
      const orderMenuList = await this.getOrderMenuById(orderId);
      return {
        orderId: ORDER_ID,
        userId: USER_ID,
        storeId: STORE_ID,
        payment: PAYMENT,
        state: STATE,
        orderDate: ORDER_DATE,
        orderMenuList: orderMenuList,
      };
    }
    return undefined;
  };
  getMaxOrderId = async (): Promise<number | undefined> => {
    type MaxOrderIdDto = {
      MAX_ORDER_ID: number;
    };
    const sql = `SELECT MAX(ORDER_ID) AS MAX_ORDER_ID FROM ORDERS`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MaxOrderIdDto>(sql)).rows;
    if (result) {
      return result[0].MAX_ORDER_ID;
    }
    return undefined;
  };
  insert = async (order: Order): Promise<Order | undefined> => {
    const { userId, storeId, orderMenuList, payment } = order;
    const orderId = (await this.getMaxOrderId())! + 1;
    const orderDate = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO ORDERS VALUES (${orderId}, ${userId}, ${storeId}, '${payment}', '주문 중', to_date('${orderDate}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    await Promise.all(
      orderMenuList!.map((orderMenu, index) => {
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
    const result = await this.getOrderById(orderId);
    return result;
  };
}

export default new OrderModel();
