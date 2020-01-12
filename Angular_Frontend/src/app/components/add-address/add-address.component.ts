import { Component, OnInit,OnDestroy } from '@angular/core';
import { NavbarService } from '../../services/navbar.service';
import { UserService } from '../../services/user.service';
import { Address } from '../../classes/address';
import { Router } from '@angular/router';
import {Location} from "@angular/common";


@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit, OnDestroy{

  newAddress: Address;
  public loading = false;
  constructor( public nav : NavbarService, public userService:UserService, public router: Router, public location: Location) {
      this.nav.show();
      this.nav.hideDropdown();
    }

  ngOnInit(){
    document.body.classList.add('nbg');

  }

  ngOnDestroy(){
    document.body.classList.remove('nbg');
  }
  addAddress(street, code, city, county){
    document.getElementById("street").style.border = "none";
    document.getElementById("postal").style.border = "none";
    document.getElementById("city").style.border = "none";
    document.getElementById("county").style.border = "none";
    street=street.trim();
    code=code.trim();
    city=city.trim();
    county=county.trim();
    if(street=="" || code=="" || city=="" || county==""){
      if(street==""){
        document.getElementById("street").style.border = "thin solid #ff4d4d";
      }
      if(code==""){
        document.getElementById("postal").style.border = "thin solid #ff4d4d";
      }
      if(city==""){
        document.getElementById("city").style.border = "thin solid #ff4d4d";
      }
      if(county==""){
        document.getElementById("county").style.border = "thin solid #ff4d4d";
      }
    }else{
      this.loading = true;
      this.newAddress=new Address();
      this.newAddress.streetAddress=street;
      this.newAddress.postalCode=code;
      this.newAddress.cityAddress=city;
      this.newAddress.countyAddress=county;
      this.userService.updateAddress(this.newAddress).subscribe(data=>{ 
        console.log(data);
        setTimeout(() => {
          this.loading = false;
          this.location.back();
        }, 3000);
      });
    }

  }
  backToOrder(){
      this.location.back();
  }

}
