import { Component, OnInit } from '@angular/core';
import {NavbarService} from "../../services/navbar.service";
import { CategoryService } from '../../services/category.service';
import { Category } from '../../classes/category';
import { Product } from '../../classes/product';
import { UserService } from '../../services/user.service';
import {CookieService} from "angular2-cookie/core";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  categories: Category[]=[];
  favoriteList: Product[]=[];

  constructor(public nav : NavbarService, private categoryService: CategoryService, private userService: UserService, public cookie: CookieService) {
    this.getAllCategory();
  }
  ngOnInit() {
  }

  getAllCategory()
  {
    this.categoryService.getAllCategorys().subscribe(data => {this.categories= data ; console.log(this.categories);
      this.favoriteList=[];
      this.userService.getAllFavoriteProducts().subscribe(data=>{this.favoriteList=data;});});
  }

  changeLanguage(flag : string){
    if(flag=='ro'){
      this.cookie.put("googtrans","/ro");
    }else if(flag=='en'){
      this.cookie.put("googtrans","/en");
    }else  if(flag=='es'){
      this.cookie.put("googtrans","/es");
    }
    location.reload();
    console.log( this.cookie.get("googtrans"));
  }
}
