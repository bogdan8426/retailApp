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
  constructor(public nav : NavbarService, public userService:UserService, public router: Router, public location: Location) {
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
    this.newAddress=new Address();
    this.newAddress.streetAddress=street;
    this.newAddress.postalCode=code;
    this.newAddress.cityAddress=city;
    this.newAddress.countyAddress=county;
    this.userService.updateAddress(this.newAddress).subscribe(data=>{ console.log(data);
    this.location.back();
    });
  }
  backToOrder(){
      this.location.back();
  }


}
