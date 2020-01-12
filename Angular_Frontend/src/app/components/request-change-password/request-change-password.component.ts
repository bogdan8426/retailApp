import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from 'src/app/classes/user';
import { UserService } from 'src/app/services/user.service';
import { P } from '@angular/core/src/render3';

@Component({
  selector: 'app-request-change-password',
  templateUrl: './request-change-password.component.html',
  styleUrls: ['./request-change-password.component.css']
})
export class RequestChangePasswordComponent implements OnInit, OnDestroy {

  userRequest: User;
  errorMessage: String;

  constructor(private userService:UserService) { }

  ngOnInit() {
    document.body.classList.add('bg');
  }

  ngOnDestroy(){
    document.body.classList.remove('bg');
  }

  requestChangePassword(name: string){
    document.getElementById('email').style.border = "none";
    this.errorMessage="";
    this.userRequest=new User();
    this.userRequest.email=name;
    this.userService.registerEmailValidation(this.userRequest).subscribe(data=>{
      if(data==null){
        this.errorMessage="This email is not associated with any registered account!";
        document.getElementById('email').style.border = "thin solid #ff4d4d";
      }else{
        this.userRequest=data;
        this.errorMessage = "You will receive an email in a few minutes. Please check your email to confirm this action!"
        this.userService.sendRequestChangePassword(this.userRequest).subscribe(data=>{

        });
      }
    })
  }

}
