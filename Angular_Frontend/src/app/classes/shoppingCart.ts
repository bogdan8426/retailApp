import {Product} from "./product";
import {User} from "./user";

export class ShoppingCart {
  shoppingId: number;
  quantity:number;
  user: User;
  product: Product;
}
