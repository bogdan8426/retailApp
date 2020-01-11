import { Component, OnInit, OnDestroy } from '@angular/core';
import { NavbarService } from '../../services/navbar.service';
import { Product } from '../../classes/product';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-favorite-list',
  templateUrl: './favorite-list.component.html',
  styleUrls: ['./favorite-list.component.css']
})
export class FavoriteListComponent implements OnInit {

  favoriteList: Product[]=[];
  constructor(public nav: NavbarService, private userService: UserService) {
    this.nav.show();
    this.nav.hideDropdown();
    this.showAllFavoriteProduct();
   }

  ngOnInit() {
    document.body.classList.add('nbg');


  }
  ngOnDestroy(){
    document.body.classList.remove('nbg');
  }

  showAllFavoriteProduct()
  {
    this.userService.getAllFavoriteProducts().subscribe(data=>{this.favoriteList=data;});
  }

  deleteProduct(productId: number){
    this.userService.deleteFromFavorite(productId).subscribe(data => { console.log(data);location.reload(); })
  }
}
