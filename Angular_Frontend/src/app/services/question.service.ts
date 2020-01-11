import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {Http, Response} from "@angular/http";
import {Question} from "../classes/question";
import {Answer} from "../classes/answer";


@Injectable()
export class QuestionService {

  constructor(private http: Http) { }

  private BASE_URL : string ="http://localhost:9000/";

  getQuestionsByProductId(productId: number) {
    return this.http.get(this.BASE_URL +'question/'+productId)
      .pipe(map((res : Response) => (<any>res)._body == '' ? null : res.json()));
  }

  insertQuestion(question : Question){
    return this.http.put(this.BASE_URL + 'question/addQuestion', question, {})
      .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));

  }

  insertAnswer(answer : Answer, questionId: number){
    return this.http.put(this.BASE_URL + 'answer/addAnswer/'+questionId, answer, {})
      .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));

  }

  deleteQuestion(questionId : number){
    return this.http.delete(this.BASE_URL+'question/removeQuestion/'+questionId)
      .pipe(map((response : Response)=> (<any>response)._body == '' ? null : response.json()));
  }

  deleteAnswer(answerId : number){
    return this.http.delete(this.BASE_URL+'answer/removeAnswer/'+answerId)
      .pipe(map((response : Response)=> (<any>response)._body == '' ? null : response.json()));
  }

  deleteAnswersByQuestionId(questionId: number){
    return this.http.delete(this.BASE_URL+'answer/removeAnswers/'+questionId)
      .pipe(map((response : Response)=> (<any>response)._body == '' ? null : response.json()));
  }


}
