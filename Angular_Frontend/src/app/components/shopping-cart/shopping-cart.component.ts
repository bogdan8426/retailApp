import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavbarService} from "../../services/navbar.service";
import {ShoppingCartService} from "../../services/shopping-cart.service";
import {ShoppingCart} from "../../classes/shoppingCart";
import {Product} from "../../classes/product";
import {User} from "../../classes/user";
import {Order} from "../../classes/order";
import {DatePipe} from "@angular/common";
import {OrderService} from "../../services/order.service";
import {OrderProducts} from "../../classes/orderProducts";
import {Rating} from "../../classes/rating";
import {RatingService} from "../../services/rating.service";


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy{

  shoppingCart : ShoppingCart[]=[];
  shop: ShoppingCart;
  i : any;
  sum : any=0;
  delivery : any= 20;
  total : any;
  order: Order;
  checkOrder: boolean;
  ratings: string[];
  ratingStars:string;
  product: Product;
  stars: any;

  constructor( public nav : NavbarService, public shoppingCartService : ShoppingCartService, public datepipe: DatePipe, public orderService: OrderService, public ratingService: RatingService) {
    this.nav.show();
    this.nav.hideDropdown();
  }

  ngOnInit() {
    document.body.classList.add('nbg');
    this.findAllShoppings();
  }

  ngOnDestroy(){
    document.body.classList.remove('nbg');

  }

  findAllShoppings(){
    this.checkOrder=true;
    this.shoppingCartService.findAllShoppings().subscribe(data => {this.shoppingCart= data ; console.log(this.shoppingCart);
    this.productSum();
    let i=0;
    if(this.shoppingCart.length>0){
    for(i=0; i<this.shoppingCart.length; i++)
    {
        if(this.shoppingCart[i].quantity>Number(this.shoppingCart[i].product.quantity))
        {
          this.checkOrder=false;
        }
    }
  }
  });
  }

  productSum(){
    for(this.i=0;this.i<this.shoppingCart.length;this.i++ ){
      this.sum=this.sum+this.shoppingCart[this.i].quantity * this.shoppingCart[this.i].product.price;
    }
    if(this.sum>1000){
      this.delivery=0;
    }
    this.total=this.delivery+this.sum;
  }

  deleteProduct(productId){
    this.shop=new ShoppingCart();
    this.shop.product=new Product();
    this.shop.product.productId=productId;
    this.shop.user=new User();
    this.shop.user.usersId=this.nav.getUserId().usersId;

    this.shoppingCartService.deletefromShoppingCart(this.shop).subscribe(data => {this.shop= data ; console.log(this.shop)});
    location.reload();
  }
  getProductRating(){
    for(let j=0;j<this.shoppingCart.length;j++){
      this.product= new Product();
      this.product.productId=this.shoppingCart[j].product.productId;
      this.ratingService.findRatingByProductId(this.product).subscribe(data => {this.ratingStars=data, console.log(this.ratingStars),
        this.ratings[j]=this.ratingStars;
        for(let i=0;i<Number(this.ratingStars);i++){
          this.stars[i]=i+1;
        }

      })
    }

  }


}
