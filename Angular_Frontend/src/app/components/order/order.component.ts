import {Component, OnDestroy, OnInit} from '@angular/core';
import {OrderService} from "../../services/order.service";
import {NavbarService} from "../../services/navbar.service";
import {Order} from "../../classes/order";
import {OrderProducts} from "../../classes/orderProducts";
import {User} from "../../classes/user";
import {ShoppingCart} from "../../classes/shoppingCart";
import {Product} from "../../classes/product";
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {ShoppingCartService} from "../../services/shopping-cart.service";
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, OnDestroy {

  order: Order;
  newOrder: Order;
  orderProducts: OrderProducts[];
  myOrder: OrderProducts;
  total : any;
  paymentMethod: string="cash";
  shoppingCart: ShoppingCart[]=[];
  user:User;
  addressSelected:string="";
  errorMsg: boolean;

  constructor(public router: Router,public nav : NavbarService,public orderService: OrderService,  private activatedRoute: ActivatedRoute, public datepipe: DatePipe, public shoppingCartService : ShoppingCartService, public userService:UserService){
    this.activatedRoute.queryParams.subscribe(data => {
      this.total= data['sumaTotala']
    });
    this.nav.show();
    this. findAllShoppings();
    this. getUserData();
    this.errorMsg=false;
    this.nav.hideDropdown();
    }

  ngOnInit() {
    document.body.classList.add('nbg');
  }

  ngOnDestroy(){
    document.body.classList.remove('nbg');

  }

  findAllShoppings(){
    this.shoppingCartService.findAllShoppings().subscribe(data => {this.shoppingCart = data;
      console.log(this.shoppingCart);});
    }

  makeOrder(){
    if(this.addressSelected=="")
    {
      this.errorMsg=true;
    }
    else
    {
      this.order=new Order();
      this.order.totalPrice=this.total;
      this.order.paymentMethod=this.paymentMethod;
      this.order.orderAddress=this.addressSelected;
      this.order.flag=false;
      let date= Date.now()
      this.order.orderDate=this.datepipe.transform(date, 'yyyy-MM-dd');
      this.order.users=new User();
      this.order.users.usersId=this.nav.getUserId().usersId;
      this.newOrder=new Order();
      this.newOrder.users=new User();
      this.orderService.addOrder(this.order).subscribe(data => {this.newOrder= data ; console.log(this.newOrder);
        this.saveOrder(this.newOrder, this.shoppingCart);});
        this.errorMsg=false;
    }
  }

  saveOrder(newOrder: Order, shoppingCart: ShoppingCart[])
  {
    this.orderProducts=[];
    for(let i=0; i<shoppingCart.length; i++)
    {
      this.myOrder=new OrderProducts();
      this.myOrder.productOrder=new Product();
      this.myOrder.orderProduct=new Order();
      this.myOrder.productOrder=shoppingCart[i].product;
      this.myOrder.orderProduct=newOrder;
      this.myOrder.quantity=shoppingCart[i].quantity;
      this.orderProducts[i]=this.myOrder;
    }
    this.orderService.addAllProductToOrder(this.orderProducts).subscribe(data => {this.myOrder= data;});
    this.orderService.sendOrderMail(this.orderProducts).subscribe(data => {this.myOrder=data;
      this.router.navigate(['/displayAllProducts']);});

  }

  setPaymentMethod(payment: string){
    this.paymentMethod=payment;
  }

  getUserData(){
    this.user=new User();
    this.user.addresses=[];
    this.userService.getUserById().subscribe(data => {this.user=data;});
  }

  getAddressId(street, postal, city, county){
    this.addressSelected=street+", "+postal+", "+city+", "+county;
    console.log(this.addressSelected)
  }
}
