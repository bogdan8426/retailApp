import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavbarService} from "../../services/navbar.service";
import {UserService} from "../../services/user.service";
import {User} from "../../classes/user";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-display-users',
  templateUrl: './display-users.component.html',
  styleUrls: ['./display-users.component.css']
})
export class DisplayUsersComponent implements OnInit, OnDestroy {

  description: string;
  users : User[];
  usersExceptLoggedOne = new Array();

  constructor(public nav : NavbarService, public userService : UserService, private router: Router) {
    this.nav.show();
    this.displayUsers();
    this.nav.hideDropdown();
  }

  ngOnInit() {
    document.body.classList.add('nbg');
  }
  ngOnDestroy(){
    document.body.classList.remove('nbg');
  }

  displayUsers(){
    this.userService.findAllUsers().subscribe(data => {this.users= data ; console.log(this.users);
    this.users.forEach(user => {
      if(user.email!=this.nav.getUserEmail().email){
        this.usersExceptLoggedOne.push(user);
        console.log(user.role.role_description);
      }
    })
    });
  }

  updateUser(email){
    this.userService.updateRole(email).subscribe(data=>{ console.log(data);});
      location.reload();
  }

  }

