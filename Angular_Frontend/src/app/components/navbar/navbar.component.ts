import { Component, OnInit } from '@angular/core';
import {NavbarService} from "../../services/navbar.service";
import { CategoryService } from '../../services/category.service';
import { Category } from '../../classes/category';
import { Product } from '../../classes/product';
import { UserService } from '../../services/user.service';
import {CookieService} from "angular2-cookie/core";
import { Subscription } from 'rxjs/internal/Subscription';
import { interval } from 'rxjs/internal/observable/interval';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  categories: Category[]=[];
  favoriteList: Product[]=[];
  subscription: Subscription;
  constructor(private router:Router,public nav : NavbarService, private categoryService: CategoryService, private userService: UserService, public cookie: CookieService) {
    this.getAllCategory();
    const source = interval(10000);
    this.subscription = source.subscribe(val=>{
      console.log("every 10 seconds");
      this.userService.getAllFavoriteProducts().subscribe(data=>{this.favoriteList=data;});
    });
  }
  ngOnInit() {
  }

  logOut(){
    this.nav.removeUserEmail();
    this.nav.removeUserId();
    this.router.navigate(['/login']);
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
