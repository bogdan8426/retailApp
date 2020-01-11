import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ProductService } from "../../services/product.service";
import { Product } from "../../classes/product";
import { NavbarService } from "../../services/navbar.service";
import { ProductDetails } from "../../classes/productDetails";
import { Category } from '../../classes/category';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit, OnDestroy {

  productId: number;
  product: Product;
  productToChange: Product;
  categoryName: string = "Laptop";
  categories: Category[] = [];
  errorMessage: string;

  constructor(private productService: ProductService, private router: Router, private activatedRoute: ActivatedRoute, public nav: NavbarService, private categoryService: CategoryService) {
    this.activatedRoute.queryParams.subscribe(data => {
      this.productId = data['productId'], console.log(this.productId)
    });
    this.nav.show();
    this.getAllCategory();
    this.nav.hideDropdown();
  }

  ngOnInit() {
    document.body.classList.add('nbg');
    this.getProductById();
  }
  ngOnDestroy() {
    document.body.classList.remove('nbg');
  }

  getProductById() {
    this.product = new Product();
    this.product.productId = this.productId;
    this.productService.getProductById(this.product).subscribe(data => {
      this.productToChange = data;
      console.log(this.productToChange)
    });
  }

  updateProduct(name, brand, quantity, price, procesor, sistemDeOperare, memorieRam, memorieInterna, rezolutie, rezolutieCamera, description, image) {
    this.errorMessage = "";
    if (this.isValidPrice(quantity) == false) {
      this.errorMessage = "Quantity need to be number and positive";
    }
    else {
      if (this.isValidPrice(price) == true) {
        this.product = new Product();
        this.product.productId = this.productId;
        this.product.name = name;
        this.product.brand = brand;
        this.product.quantity = quantity;
        this.product.price = price;
        this.product.image = image;
        this.product.productDetails = new ProductDetails();
        this.product.productDetails.productDetailsId = this.productToChange.productDetails.productDetailsId;
        this.product.productDetails.procesor = procesor;
        this.product.productDetails.sistemDeOperare = sistemDeOperare;
        this.product.productDetails.memorieRam = memorieRam;
        this.product.productDetails.memorieInterna = memorieInterna;
        this.product.productDetails.rezolutie = rezolutie;
        this.product.productDetails.rezolutieCamera = rezolutieCamera;
        this.product.productDetails.description = description;
        this.product.category = new Category();
        this.categoryService.getCategoryByName(this.categoryName).subscribe((data: Category) => {
          this.product.category.categoryId = data.categoryId;
          this.product.category.categoryName = data.categoryName;
          console.log(data.categoryId);
          this.productService.updateProduct(this.product).subscribe(data => {
            console.log(data);
            this.router.navigate(['/displayAllProducts']);
          });
        });
      }else{
        this.errorMessage = "Price need to be number and positive";
      }
    }
  }

  getAllCategory() {
    this.categoryService.getAllCategorys().subscribe(data => { this.categories = data; console.log(this.categories) });
  }

  setNewCategory(categoryName: string) {
    this.categoryName = categoryName;
  }
  isValidPrice(price) {
    return new RegExp('^[0-9]+$').test(price);
  }
}
