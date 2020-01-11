import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { map } from 'rxjs/operators';

import { User } from '../classes/user';
import {NavbarService} from "./navbar.service";
import { Address } from '../classes/address';

@Injectable()

export class UserService {

  constructor(private http: Http, public nav : NavbarService) {}

  private BASE_URL : string ="http://localhost:9000/user/";


  findAllUsers() {
  		return this.http.get(this.BASE_URL +'allUsers')
  	  					.pipe(map((response : Response) => <User[]>response.json()));
  }

  loginValidation(user : User){
    return this.http.post(this.BASE_URL+ 'getUserByPasswordAndEmail',user, {})
      .pipe(map((res : Response) => (<any>res)._body == '' ? null : res.json()));

  }

  registerEmailValidation(user : User){
    return this.http.post(this.BASE_URL+ 'getUserByEmail',user,{})
      .pipe(map((res : Response) => (<any>res)._body == '' ? null : res.json()));
  }

  registerUser(user : User) {
      return this.http.put(this.BASE_URL+ 'addUser', user, {}) // using post request
                 .pipe(map((res:Response) => (<any>res)._body == '' ? null : res.json())); //and calling .json() on the response to return data
  }

  getRoleDescription(user:string) {
    return this.http.post(this.BASE_URL + 'getDescription',user,{})
      .pipe(map((response: Response) =>response.text()));
  }


  updateRole(email : string) {
    return this.http.post(this.BASE_URL+ 'updateRole',email,{})
      .pipe(map((response : Response)=> response.json()));
  }

  getUserById(){
    let id=this.nav.getUserId().usersId;
    return this.http.get(this.BASE_URL +'/'+id)
      .pipe(map((response : Response) => response.json()));
  }

  updateAddress(address : Address) {
    let id=this.nav.getUserId().usersId;
    return this.http.post(this.BASE_URL+"updateAddress/"+id,address,{})
      .pipe(map((response : Response)=>(<any>response)._body == '' ? null : response.json()));
  }

  addToFavorite(productId:number)
  {
    let id=this.nav.getUserId().usersId;
    return this.http.post(this.BASE_URL+"addFavorite/"+id,productId,{})
    .pipe(map((response : Response)=> response.json()));
  }

  checkFavorite(productId:number)
  {
    let id=this.nav.getUserId().usersId;
    return this.http.post(this.BASE_URL+"checkFavorite/"+id,productId,{})
    .pipe(map((response:Response)=>response.text()));
  }

  deleteFromFavorite(productId:number)
  {
    let id=this.nav.getUserId().usersId;
    return this.http.delete(this.BASE_URL+'remove/'+productId+'/'+id)
      .pipe(map((response : Response)=> response.json()));
  }

  getAllFavoriteProducts()
  {
    let id=this.nav.getUserId().usersId;
    return this.http.get(this.BASE_URL+id+"/allProductFav")
    .pipe(map((response : Response)=> (<any>response)._body == '' ? null : response.json()));
  }
}

