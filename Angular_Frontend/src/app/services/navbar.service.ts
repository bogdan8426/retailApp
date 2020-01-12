import { Injectable } from '@angular/core';

@Injectable()
export class NavbarService {

  visible: boolean;
  email: string;
  navDropdown: boolean;

  constructor() { this.visible = false; }

  hide() { this.visible = false; }

  show() { this.visible = true; }

  hideDropdown(){this.navDropdown=false;}
  
  showDropdown(){this.navDropdown=true;}

  setUserEmail(data: any) {
    localStorage.setItem('email', JSON.stringify(data));
  }

  getUserEmail() {
    let data = localStorage.getItem('email');
    return JSON.parse(data);
  }

  removeUserEmail(){
    localStorage.removeItem(localStorage.getItem('email'));
  }

  setUserId(data: any) {
    localStorage.setItem('usersId', JSON.stringify(data));
  }

  getUserId() {
    let data = localStorage.getItem('usersId');
    return JSON.parse(data);
  }

  removeUserId(){
    localStorage.removeItem(localStorage.getItem('usersId'));
  }

  setUseRole(data: any) {
    localStorage.setItem('role', JSON.stringify(data));
  }

  getUserRole() {
    let data = localStorage.getItem('role');
    return JSON.parse(data);
}

}
