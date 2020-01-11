import { Component, OnInit, OnDestroy } from '@angular/core';
import { ProductService } from '../../services/product.service';
import {Product} from "../../classes/product";
import {NavbarService} from "../../services/navbar.service";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import { Category } from '../../classes/category';
import { CategoryService } from '../../services/category.service';
import {RatingService} from "../../services/rating.service";


@Component({
  selector: 'app-display-all-products',
  templateUrl: './display-all-products.component.html',
  styleUrls: ['./display-all-products.component.css']
})
export class DisplayAllProductsComponent implements OnInit, OnDestroy {

  products : Product[];
  description: string;
  admin:boolean;
  category: string;
  favoriteProducts: Product[]=[];
  checkFav: string;
  categories: Category[]=[];
  ratingStars:string;
  stars: number[]=[];
  constructor(private productService: ProductService, public nav : NavbarService, private userService: UserService, private activatedRoute: ActivatedRoute,private ratingService: RatingService, private categoryService: CategoryService) {
    this.activatedRoute.queryParams.subscribe(data => {
      this.category = data['category'];
      this.displayProducts();
    });
    this.nav.show();
    this.admin=false;
    this.nav.hideDropdown();
    this.getAllFavProduct();
    this.getAllCategory()
  }

  ngOnInit() {
    document.body.classList.add('nbg');

  }
  ngOnDestroy(){
    document.body.classList.remove('nbg');
  }

  displayProducts(){
    console.log(this.nav.getUserEmail().email);

    this.userService.getRoleDescription(this.nav.getUserEmail().email).subscribe( data => {this.description= data ; console.log(this.description);
      if(this.description=="admin") {
        this.admin = true;
      }
    })

    if(this.category == null){
      this.productService.displayAllProducts().subscribe(data => {this.products= data ; console.log(this.products);
      });
    }else{
      this.productService.getProductByCategoryId(Number(this.category)).subscribe(data => {this.products= data ; console.log(this.products);
      });
    }
  }

  removeProduct(product){
    this.productService.removeProduct(product).subscribe(data => { console.log(data);location.reload(); });
  }

  checkProductFav(product: Product)
  {
    this.checkFav="false";
    for(let i=0; i<this.favoriteProducts.length;i++)
    {
      if(this.favoriteProducts[i].productId==product.productId)
      {
        this.checkFav="true";
      }
    }
    return this.checkFav;
  }

  getAllFavProduct()
  {
    this.userService.getAllFavoriteProducts().subscribe(data=>{this.favoriteProducts=data;});
  }

  addToFavorite(product: Product){
    if(this.checkProductFav(product)=="false")
    {
       this.userService.addToFavorite(product.productId).subscribe(data=>{location.reload(); console.log(data)});
    }
    else{
      this.userService.deleteFromFavorite(product.productId).subscribe(data => { console.log(data);location.reload(); });
    }
  }
  getAllCategory()
  {
    this.categoryService.getAllCategorys().subscribe(data => {this.categories= data ; console.log(this.categories)});
  }


  getProductRating(product: Product) {
    this.ratingService.findRatingValueByProductId(product).subscribe(data => {this.ratingStars=data
      for(let i=0;i<Number(this.ratingStars);i++){
        this.stars[i]=i+1;
      }
    })
  }


}
