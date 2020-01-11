import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';

import { map } from 'rxjs/operators';
import {Product} from "../classes/product";
import {Rating} from "../classes/rating";


@Injectable()
export class RatingService {

  constructor(private http: Http) {}

  private BASE_URL : string ="http://localhost:9000/rating/";


  findRatingValueByProductId(product : Product) {
    return this.http.get(this.BASE_URL + product.productId)
      .pipe(map((response : Response) => response.text()));
  }

  findRatingByProductId(product : Product) {
    return this.http.get(this.BASE_URL +'comm/'+ product.productId)
      .pipe(map((response : Response) => (<any>response)._body == '' ? null : response.json()));
  }

  insertRating(rating : Rating) {
    return this.http.put(this.BASE_URL+ 'addRating', rating, {}) // using post request
      .pipe(map((res:Response) => res.text())); //and calling .json() on the response to return data
  }

}
