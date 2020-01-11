import { Component, OnInit, OnDestroy } from '@angular/core';
import { ProductService } from "../../services/product.service";
import { NavbarService } from "../../services/navbar.service";
import { Router } from "@angular/router";
import { Product } from "../../classes/product";
import { ProductDetails } from "../../classes/productDetails";
import { Category } from '../../classes/category';
import { CategoryService } from '../../services/category.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit, OnDestroy {

  product: Product;
  errorMessage: string="";
  categoryName: string = "Laptop";
  categories: Category[] = [];
  constructor(private spinner: NgxSpinnerService,private productService: ProductService, private router: Router, public nav: NavbarService, private categoryService: CategoryService) {
    this.nav.show();
    this.getAllCategory();
    this.nav.hideDropdown();
  }

  ngOnInit() {
    document.body.classList.add('nbg');
  }
  ngOnDestroy() {
    document.body.classList.remove('nbg');
  }

  addProduct(name, brand, quantity, price, procesor, sistemDeOperare, memorieRam, memorieInterna, rezolutie, rezolutieCamera, description, image) {
    console.log(parseInt(price));
    if (this.isValidPrice(price)==true) {
      if (this.isValidPrice(quantity)==true) {
        this.product = new Product();
        this.product.name = name;
        this.product.brand = brand;
        this.product.quantity = quantity;
        this.product.price = price;
        this.product.image = image;
        this.product.productDetails = new ProductDetails();
        this.product.productDetails.procesor = procesor;
        this.product.productDetails.sistemDeOperare = sistemDeOperare;
        this.product.productDetails.memorieRam = memorieRam;
        this.product.productDetails.memorieInterna = memorieInterna;
        this.product.productDetails.rezolutie = rezolutie;
        this.product.productDetails.rezolutieCamera = rezolutieCamera;
        this.product.productDetails.description = description;
        this.product.category = new Category();
        if (name != '' && quantity != '' && price != '' && brand != '' && sistemDeOperare != '' && memorieRam != '' && memorieInterna != '' && rezolutie != '' && rezolutieCamera != '' && description != '') {
          this.categoryService.getCategoryByName(this.categoryName).subscribe((data: Category) => {
            this.product.category.categoryId = data.categoryId;
            this.product.category.categoryName = data.categoryName;
            this.productService.addProduct(this.product).subscribe(data => {
              console.log(data);
              this.spinner.show();
              setTimeout(() => {
                console.log("intra in spinner");
                  this.spinner.hide();
                  this.router.navigate(['/displayAllProducts']);
              }, 2000); 

            });
          });
        } else {
          this.errorMessage = 'All fields are required.';
        }
      }
      else {
        this.errorMessage = 'Quantity need to be Number and Positive';
      }
    }
    else {
      this.errorMessage = 'Price need to be Number and Positive';
    }
  }

  setNewCategory(categoryName: string) {
    this.categoryName = categoryName;
  }

  getAllCategory() {
    this.categoryService.getAllCategorys().subscribe(data => { this.categories = data; console.log(this.categories) });
  }

  isValidPrice(price) {
    return new RegExp('^[0-9]+$').test(price);
   }
}
