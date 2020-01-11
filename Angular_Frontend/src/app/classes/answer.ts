import {Question} from "./question";
import {User} from "./user";
export class Answer{
  answerId: number;
  answerMessage: string;
  question: Question;
  user: User;
}
