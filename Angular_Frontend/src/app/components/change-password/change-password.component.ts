import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/classes/user';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit, OnDestroy {

  userToChange:User;
  errorMessage: String;
  public loading = false;
  constructor(private activatedRoute: ActivatedRoute, private userService:UserService, private router:Router,private spinner: NgxSpinnerService) {
    this.userToChange = new User();
    this.activatedRoute.queryParams.subscribe(data => {
      this.userToChange.email = data['emailAccount'];
      console.log(this.userToChange.email);
    })
   }

  ngOnInit() {
    document.body.classList.add('bg');
  }
  
  ngOnDestroy(){
    document.body.classList.remove('bg');
  }

  changePassword(psd: string, psd2: string) {
    this.errorMessage = '';
    document.getElementById('psd').style.border = "none";
    document.getElementById('psd2').style.border = "none";
    if (psd != '' && psd2 != '') {
      if (psd.length < 6) {
        this.errorMessage = "Password must have 6 characters or more!";
        document.getElementById('psd').style.border = "thin solid #ff4d4d";
      } else if (psd2.length < 6) {
        this.errorMessage = "Password must have 6 characters or more!";
        document.getElementById('psd2').style.border = "thin solid #ff4d4d";
      } else if (psd != psd2) {
        this.errorMessage = "Password must be the same!";
        document.getElementById('psd').style.border = "thin solid #ff4d4d";
        document.getElementById('psd2').style.border = "thin solid #ff4d4d";
      } else {
        this.userToChange.password = psd;
         this.loading = true;
        this.userService.changePasswordAccount(this.userToChange).subscribe(data => {
          setTimeout(() => {
            this.loading = false;
            this.router.navigate(['/login']);
          }, 3000);
        })
      }
    } else {
      this.errorMessage = "You need to complete both fields for password!";
      document.getElementById('psd').style.border = "thin solid #ff4d4d";
      document.getElementById('psd2').style.border = "thin solid #ff4d4d";
    }
  }

}
