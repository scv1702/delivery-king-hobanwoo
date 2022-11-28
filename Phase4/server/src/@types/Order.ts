import { OrderMenu } from "./OrderMenu";

export type Order = {
  orderId?: number;
  userId: number;
  storeId: number;
  storeName?: string;
  payment: string;
  state?: string;
  orderDate?: string;
  orderMenuList?: OrderMenu[];
};
