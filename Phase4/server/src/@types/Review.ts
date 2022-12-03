import { OrderMenu } from "./OrderMenu";
import { User } from "./User";

export type Review = {
  reviewId?: number;
  userId?: number;
  user?: User;
  storeId?: number;
  starRating?: number;
  comments?: string;
  createdAt?: string;
  orderMenuList?: OrderMenu[];
};
