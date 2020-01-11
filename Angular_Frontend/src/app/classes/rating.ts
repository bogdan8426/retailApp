import {User} from "./user";
import {Product} from "./product";
export class Rating{
  ratingId: number;
  ratingStars: string;
  comment: string;
  user: User;
  product: Product;

}
