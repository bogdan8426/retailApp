import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { map } from 'rxjs/operators';
import {ShoppingCart} from "../classes/shoppingCart";
import {NavbarService} from "./navbar.service";

@Injectable()
export class ShoppingCartService {

  usersId: number;

  constructor(private http: Http,  public nav : NavbarService) {

  }

  private BASE_URL : string ="http://localhost:9000/shop/";


  findAllShoppings() {
    this.usersId =this.nav.getUserId().usersId;
    return this.http.get(this.BASE_URL +'productsShop/'+this.usersId)
      .pipe(map((response : Response) => <ShoppingCart[]>response.json()));
  }

  addToShoppingCart(shoppingCart : ShoppingCart) {
    return this.http.put(this.BASE_URL + 'addProductToList',shoppingCart,{})
      .pipe(map((res: Response) => res.text()));
  }

  deletefromShoppingCart(shoppingCart : ShoppingCart){
    return this.http.delete(this.BASE_URL + 'deleteShoppCart/'+ shoppingCart.user.usersId+'/'+shoppingCart.product.productId)
      .pipe(map((response : Response) => (<any>response)._body == '' ? null : response.json()));
  }
}
