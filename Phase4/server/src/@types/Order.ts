import { OrderMenu } from "./OrderMenu";

export type Order = {
  orderId?: number;
  userId: number;
  storeId: number;
  payment: string;
  state?: string;
  orderDate?: string;
  orderMenuList?: OrderMenu[];
};
