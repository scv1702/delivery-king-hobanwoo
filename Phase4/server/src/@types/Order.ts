import { Store } from "express-session";
import { OrderMenu } from "./OrderMenu";

export type Order = {
  orderId?: number;
  userId: number;
  storeId: number;
  store?: Store;
  payment: string;
  state?: string;
  orderDate?: string;
  orderMenuList?: OrderMenu[];
  isReviewed?: boolean;
};
