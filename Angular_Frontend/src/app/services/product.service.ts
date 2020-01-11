import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {Http, Response} from "@angular/http";
import {Product} from "../classes/product";

@Injectable()
export class ProductService {

  constructor(private http: Http) {}

  private BASE_URL : string ="http://localhost:9000/product/";

  displayAllProducts() {
    return this.http.get(this.BASE_URL +'allProduct')
      .pipe(map((response : Response) => response.json() ));
  }
  removeProduct(product : Product){
    return this.http.delete(this.BASE_URL+'remove/'+product.productId)
      .pipe(map((response : Response)=> (<any>response)._body == '' ? null : response.json()));
  }

  updateProduct(product : Product) {
    return this.http.post(this.BASE_URL+ 'updateProduct',product,{})
      .pipe(map((response : Response)=> response.json()));
  }

  getProductById(product : Product){
    return this.http.get(this.BASE_URL +product.productId)
      .pipe(map((response : Response) => response.json() ));
  }

  addProduct(product : Product){
    return this.http.put(this.BASE_URL +'addProduct',product,{})
      .pipe(map((response : Response) => response.json() ));
  }

  getProductByCategoryId(category: number){
    return this.http.get(this.BASE_URL +'/category/'+category)
      .pipe(map((response : Response) => (<any>response)._body == '' ? null : response.json()));
  }
}
