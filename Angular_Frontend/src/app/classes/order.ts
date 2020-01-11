import {User} from "./user";
import {OrderProducts} from "./orderProducts";
export class Order{
  orderId: number;
  orderDate: string;
  paymentMethod: string;
  totalPrice: string;
  orderAddress: string;
  users: User;
  flag: boolean;
}
