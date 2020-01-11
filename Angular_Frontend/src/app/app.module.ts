import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ProductService } from './services/product.service';
import { RegisterComponent } from './components/register/register.component';
import { AppRoutingModule } from './/app-routing.module';
import { UserService } from './services/user.service';
import { HomeComponent } from './components/home/home.component';
import { LogoutComponent } from './components/logout/logout.component';
import { DisplayAllProductsComponent } from './components/display-all-products/display-all-products.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import {NavbarService} from "./services/navbar.service";
import { AddProductComponent } from './components/add-product/add-product.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';
import {DisplayProductDetailsComponent} from "./components/display-product-details/display-product-details.component";
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import {ShoppingCartService} from "./services/shopping-cart.service";
import {DatePipe} from "@angular/common";
import {OrderService} from "./services/order.service";
import { OrderComponent } from './components/order/order.component';
import { AddAddressComponent } from './components/add-address/add-address.component';
import { CategoryService } from './services/category.service';
import { RatingService } from './services/rating.service';
import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';
import { QuestionService } from './services/question.service';
import {CookieService} from "angular2-cookie/core";
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgxLoadingModule } from 'ngx-loading';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    LogoutComponent,
    DisplayAllProductsComponent,
    UpdateProductComponent,
    AddProductComponent,
    NavbarComponent,
    DisplayUsersComponent,
    DisplayProductDetailsComponent,
    ShoppingCartComponent,
    OrderComponent,
    AddAddressComponent,
    FavoriteListComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    NgxSpinnerModule,
    NgxLoadingModule.forRoot({})
  ],
  providers: [UserService, ProductService, NavbarService,ShoppingCartService,DatePipe, OrderService, CategoryService, RatingService, QuestionService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
