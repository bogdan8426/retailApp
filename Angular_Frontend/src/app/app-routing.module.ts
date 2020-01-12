import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import {LogoutComponent} from "./components/logout/logout.component";
import {DisplayAllProductsComponent} from "./components/display-all-products/display-all-products.component";
import {UpdateProductComponent} from "./components/update-product/update-product.component";
import {AddProductComponent} from "./components/add-product/add-product.component";
import {DisplayUsersComponent} from "./components/display-users/display-users.component";
import {DisplayProductDetailsComponent} from "./components/display-product-details/display-product-details.component";
import {ShoppingCartComponent} from "./components/shopping-cart/shopping-cart.component";
import {OrderComponent} from "./components/order/order.component";
import { AddAddressComponent } from './components/add-address/add-address.component';
import { FavoriteListComponent } from './components/favorite-list/favorite-list.component';
import { RequestChangePasswordComponent } from './components/request-change-password/request-change-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';


const routes: Routes = [
	{ path: '', redirectTo: '/login', pathMatch: 'full'},
	{ path: 'login', component: LoginComponent },
	{ path: 'register', component: RegisterComponent },
	{ path: 'home', component: HomeComponent },
  { path: 'logOut', component: LogoutComponent },
  { path: 'displayAllProducts', component: DisplayAllProductsComponent },
  { path: 'updateProduct', component: UpdateProductComponent  },
  { path: 'addProduct', component: AddProductComponent  },
  { path: 'displayUsers', component: DisplayUsersComponent},
  { path: 'displayProductDetails', component: DisplayProductDetailsComponent},
  { path: 'shoppingCart', component: ShoppingCartComponent},
  { path: 'order', component: OrderComponent},
  { path: 'addAddress', component: AddAddressComponent},
  { path:'favoriteList',component: FavoriteListComponent},
  { path: 'requestPassword', component:RequestChangePasswordComponent},
  { path: 'changePasswordAccount', component: ChangePasswordComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
