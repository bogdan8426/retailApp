import {ProductDetails} from "./productDetails";
import { Category } from "./category";
export class Product {
  productId: number;
  name: string;
  brand: string;
  quantity: string;
  price: number;
  productDetails: ProductDetails;
  category: Category;
  image: string;
}
