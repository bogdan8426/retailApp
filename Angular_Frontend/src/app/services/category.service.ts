import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {Http, Response} from "@angular/http";

@Injectable()
export class CategoryService {

  constructor(private http: Http) { }

  private BASE_URL : string ="http://localhost:9000/category/";

  getAllCategorys() {
    return this.http.get(this.BASE_URL +'allCategory')
      .pipe(map((response : Response) => response.json()));
  }

  getCategoryByName(categoryName : string){
    console.log(categoryName);
    return this.http.get(this.BASE_URL +"name/"+categoryName)
      .pipe(map((response : Response) => response.json() ));
  }
}
