import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {NavbarService} from "../../services/navbar.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  constructor(public nav : NavbarService) {
    this.nav.show();
    this.nav.showDropdown();
  }

  ngOnInit() {
    document.body.classList.add('bg');
  }
  ngOnDestroy(){
    document.body.classList.remove('bg');
  }


}
