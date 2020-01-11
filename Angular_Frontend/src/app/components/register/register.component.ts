import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../classes/user';
import { Router } from "@angular/router";
import { NavbarService } from "../../services/navbar.service";
import {OrderService} from "../../services/order.service";
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit, OnDestroy {

  users: User[];
  user: User;
  responseStatus: Object = [];
  foundUser: User;
  errorMessage: string = "";
  mailStatus: any;
  public loading = false;
  constructor(private spinner: NgxSpinnerService,private userService: UserService, private router: Router, public nav: NavbarService, public orderService: OrderService) {
    this.nav.hide();
    this.nav.hideDropdown();
  }

  ngOnInit() {
    this.findAllUsers();
    document.body.classList.add('bg');
  }

  ngOnDestroy() {
    document.body.classList.remove('bg');
  }

  findAllUsers() {
    this.userService.findAllUsers().subscribe(data => { this.users = data; console.log(this.users) });
  }

  registerCheck(firstName, lastName, phoneNumber, email, password) {
    this.user = new User;
    this.user.firstName = this.escapeHtml(firstName);
    this.user.lastName = this.escapeHtml(lastName);
    this.user.email = this.escapeHtml(email);
    this.user.password = this.escapeHtml(password);
    this.user.phoneNumber = this.escapeHtml(phoneNumber);
    document.getElementById("first_name").style.border = "none";
    document.getElementById("last_name").style.border = "none";
    document.getElementById("phone_number").style.border = "none";
    document.getElementById("email").style.border = "none";
    document.getElementById("psw").style.border = "none";
    if (this.user.lastName != "" && this.user.firstName != "" && this.user.email != "" && this.user.password != "" && this.user.phoneNumber != "") {
      if (this.user.password.length >= 5 && this.user.password.length <= 15) {
        if (this.isValidEmail(this.user.email) == true) {
          if (this.isValidPhoneNumber(this.user.phoneNumber) == true) {
            this.userService.registerEmailValidation(this.user).subscribe(data => {
            this.foundUser = data; console.log(data); this.registerCheckEmail();
            });
          }
          else {
            document.getElementById("phone_number").style.border = "thin solid #ff4d4d";
            this.errorMessage = 'Invalid phone number';
          }
        } else {
          document.getElementById("email").style.border = "thin solid #ff4d4d";
          this.errorMessage = 'Invalid form of email.';
        }
      } else {
        document.getElementById("psw").style.border = "thin solid #ff4d4d";
        this.errorMessage = 'Password must be between 5 and 15 characters.';
      }
    } else {
      if(this.user.lastName==""){
        document.getElementById("last_name").style.border = "thin solid #ff4d4d";
      }
      if(this.user.firstName==""){
        document.getElementById("first_name").style.border = "thin solid #ff4d4d";
      }
      if(this.user.email==""){
        document.getElementById("email").style.border = "thin solid #ff4d4d";
      }
      if(this.user.password==""){
        document.getElementById("psw").style.border = "thin solid #ff4d4d";
      }
      if(this.user.phoneNumber==""){
        document.getElementById("phone_number").style.border = "thin solid #ff4d4d";
      }
      this.errorMessage = 'You have to complete all the fields.';
    }
  }
  registerCheckEmail() {
    if (this.foundUser != null) {
      this.errorMessage = 'Email already exists';
      document.getElementById("email").style.border = "thin solid #ff4d4d";
    } else {
      this.loading = true;
      this.errorMessage = 'Processing....';
      this.registerUser();
    }
  }

  registerUser() {
    this.userService.registerUser(this.user).subscribe(data => {
      console.log(this.responseStatus = data)
      this.orderService.sendRegisterMail(this.user).subscribe(data => {
        this.loading = false;
        this.router.navigate(['/login']);
      });});

  }

  escapeHtml(text) {
    return text.replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;")
      .replace(/"/g, "&quot;")
      .replace(/'/g, "&#039");
  }

  isValidPhoneNumber(phone_number) {
    return /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im.test(phone_number);
  }

  isValidEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }
}
