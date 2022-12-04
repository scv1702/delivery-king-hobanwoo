import moment from "moment";
import database from "../database";
import { getCurrentKorDate } from "../utils";
import MenuModel from "./MenuModel";
import { Order } from "../@types/Order";
import { OrderMenu } from "../@types/OrderMenu";
import { getAllResolvedResult } from "../utils";
import StoreModel from "./StoreModel";
import ReviewModel from "./ReviewModel";
import { isDBError } from "../utils";

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
  ): Promise<OrderMenu[] | undefined> => {
    const sql = `SELECT * FROM ORDER_MENU WHERE ORDER_ID = ${orderId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderMenuDto>(sql)).rows;
    const orderMenuList = result?.map((orderMenu: OrderMenuDto) => {
      return {
        orderMenuId: orderMenu.ORDER_MENU_ID,
        orderId: orderMenu.ORDER_ID,
        menuName: orderMenu.MENU_NAME,
        image: orderMenu.MENU_IMAGE,
        price: orderMenu.MENU_PRICE,
        quantity: orderMenu.QUANTITY,
      } as OrderMenu;
    });
    return orderMenuList;
  };
  getOrders = async (sql: string): Promise<Order[] | undefined> => {
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderDto>(sql)).rows;
    const orders = result?.map((order: OrderDto) => {
      return {
        orderId: order.ORDER_ID,
        userId: order.USER_ID,
        storeId: order.STORE_ID,
        payment: order.PAYMENT,
        state: order.STATE,
        orderDate: order.ORDER_DATE,
      } as Order;
    });
    const ordersWithOrderMenu = orders?.map((order) => {
      return new Promise<Order>((resolve) => {
        this.getOrderMenuById(order.orderId!).then((orderMenuList) => {
          resolve({ ...order, orderMenuList } as Order);
        });
      });
    });
    const ordersWithOrderMenuResolved = await getAllResolvedResult(
      ordersWithOrderMenu
    );
    const ordersWithStore = ordersWithOrderMenuResolved?.map((order) => {
      return new Promise<Order>((resolve) => {
        StoreModel.getStoreById(order.storeId!).then((store) => {
          resolve({ ...order, store } as Order);
        });
      });
    });
    const ordersWithStoreResolved = await getAllResolvedResult(ordersWithStore);
    const ordersWithIsReviewd = ordersWithStoreResolved?.map((order) => {
      return new Promise<Order>((resolve, reject) => {
        ReviewModel.getReviewByOrderId(order.orderId!).then((review) => {
          resolve({ ...order, review } as Order);
        });
      });
    });
    return getAllResolvedResult(ordersWithIsReviewd);
  };
  getOrderById = async (orderId: number): Promise<Order | undefined> => {
    const sql = `SELECT * FROM ORDERS WHERE ${orderId} = ORDER_ID`;
    const result = await this.getOrders(sql);
    return result?.[0];
  };
  getMaxOrderId = async (): Promise<number | undefined> => {
    type MaxOrderIdDto = {
      MAX_ORDER_ID: number;
    };
    const sql = `SELECT MAX(ORDER_ID) AS MAX_ORDER_ID FROM ORDERS`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MaxOrderIdDto>(sql)).rows;
    return result?.[0].MAX_ORDER_ID;
  };
  insert = async (order: Order): Promise<Order | undefined> => {
    const { userId, storeId, orderMenuList, payment } = order;
    const orderId = (await this.getMaxOrderId())! + 1;
    const orderDate = moment(getCurrentKorDate()).format("YYYY-MM-DD hh:mm:ss");
    const sql = `INSERT INTO ORDERS VALUES (${orderId}, ${userId}, ${storeId}, '${payment}', '주문 중', to_date('${orderDate}', 'YYYY-MM-DD hh24:mi:ss'))`;
    const conn = await database.getConnection();
    await conn.execute(sql);
    try {
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
      return await this.getOrderById(orderId);
    } catch (err) {
      if (isDBError(err)) {
        console.error(err.message);
        await conn.rollback();
      }
      return undefined;
    }
  };
  getOrdersByUserId = async (userId: number): Promise<Order[] | undefined> => {
    const sql = `SELECT * FROM ORDERS WHERE USER_ID = ${userId}`;
    return await this.getOrders(sql);
  };
  getOrderMenuIdsByOrderId = async (orderId: number) => {
    const sql = `SELECT ORDER_MENU_ID FROM ORDER_MENU WHERE ORDER_ID = ${orderId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<{ ORDER_MENU_ID: number }>(sql)).rows;
    return result?.map((orderMenu) => orderMenu.ORDER_MENU_ID);
  };
  getOrderMenuByOrderMenuKey = async (
    orderMenuId: number,
    orderId: number
  ): Promise<OrderMenu | undefined> => {
    const sql = `SELECT * FROM ORDER_MENU WHERE ORDER_MENU_ID = ${orderMenuId} AND ORDER_ID = ${orderId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<OrderMenuDto>(sql)).rows?.[0];
    if (result) {
      return {
        orderMenuId: result.ORDER_MENU_ID,
        orderId: result.ORDER_ID,
        menuName: result.MENU_NAME,
        image: result.MENU_IMAGE,
        price: result.MENU_PRICE,
        quantity: result.QUANTITY,
      } as OrderMenu;
    } else {
      return undefined;
    }
  };
  getOrderMenuByReviewId = async (reviewId: number) => {
    const sql = `SELECT ORDER_ID, ORDER_MENU_ID FROM CONTAINS WHERE REVIEW_ID = ${reviewId}`;
    const conn = await database.getConnection();
    const orderMenuKeyList = (
      await conn.execute<{ ORDER_ID: number; ORDER_MENU_ID: number }>(sql)
    ).rows; // ORDER_MENU_ID[]
    const orderMenuList = orderMenuKeyList?.map((obj) => {
      const orderMenuId = obj.ORDER_MENU_ID;
      const orderId = obj.ORDER_ID;
      return new Promise<OrderMenu>((resolve) => {
        this.getOrderMenuByOrderMenuKey(orderMenuId!, orderId!).then(
          (orderMenu) => {
            resolve(orderMenu as OrderMenu);
          }
        );
      });
    });
    return getAllResolvedResult(orderMenuList);
  };
}

export default new OrderModel();
