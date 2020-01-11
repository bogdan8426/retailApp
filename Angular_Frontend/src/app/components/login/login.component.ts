import {Component, OnDestroy, OnInit} from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../classes/user';
import { Router } from "@angular/router";
import {NavbarService} from "../../services/navbar.service";
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit , OnDestroy{

  constructor(private spinner: NgxSpinnerService,private userService : UserService ,private router: Router, public nav: NavbarService){
    this.nav.hide();
    this.nav.hideDropdown();
  }

  user : User;
  foundUser : User;
  errorMessage : string;
  description: string;

  ngOnInit() {
    document.body.classList.add('bg');
  }

  ngOnDestroy(){
    document.body.classList.remove('bg');
  }

  loginValidation(email,password) {
    this.user = new User();
    this.user.email = email;
    this.user.password = password;
    this.nav.email = email;

    this.userService.loginValidation(this.user).subscribe(data => {
      this.foundUser = data;
      document.getElementById("email").style.border = "none";
      if(this.foundUser!=null){
        console.log(data);
        this.nav.setUserEmail(this.user);
        this.nav.setUserId(this.foundUser);
        this.setUserRole();
        console.log(this.nav.getUserId().usersId);
        this.loginCheck();
      }else{
        document.getElementById("email").style.border = "thin solid #ff4d4d";
        this.errorMessage='Email is not used';
      }
    })
  }

  loginCheck(){
    document.getElementById("psw").style.border = "none";
  	if(this.foundUser!=null){
      this.spinner.show();
      setTimeout(() => {
        console.log("intra in spinner");
          this.spinner.hide();
          this.router.navigate(['/home']);
      }, 2000); 
  	}else{
      document.getElementById("psw").style.border = "thin solid #ff4d4d";
  		this.errorMessage='Incorrect password';
  	}
  }

  setUserRole(){
    this.userService.getRoleDescription(this.nav.getUserEmail().email).subscribe( data => {this.description= data ; console.log(this.description);
      this.nav.setUseRole(this.description);
    })
  }

}
