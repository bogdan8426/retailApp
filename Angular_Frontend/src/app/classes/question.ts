import {User} from "./user";
import {Product} from "./product";
import {Answer} from "./answer";
export class Question {
  questionId: number;
  questionMessage: string;
  user: User;
  product: Product;
  answers: Answer[];

}
