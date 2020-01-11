import { Injectable } from '@angular/core';
import {Order} from "../classes/order";
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { map } from 'rxjs/operators';
import {OrderProducts} from "../classes/orderProducts";
import {User} from "../classes/user";


@Injectable()
export class OrderService {

  private BASE_URL : string ="http://localhost:9000/";

  constructor(private http: Http) { }

  addOrder(order : Order) {
    return this.http.put(this.BASE_URL+ 'order/addOrder', order, {}) // using post request
      .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));
  }

  addAllProductToOrder(orderProduct : OrderProducts[]) {
    return this.http.put(this.BASE_URL+ 'orderProduct/addOrderProducts', orderProduct, {})
      .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));
  }
  sendOrderMail(orderProducts : OrderProducts[]){
     return this.http.put(this.BASE_URL+ 'email/sendOrderMail', orderProducts, {})
           .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));
  }
  sendRegisterMail(user: User){
    return this.http.put(this.BASE_URL+ 'email/sendRegisterMail', user, {})
      .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json()));
  }

}
