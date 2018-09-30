import { Component, OnInit } from '@angular/core';
import { Router } from '../../../../../node_modules/@angular/router';
import { CurrentUser } from '../../modules/users/model/curentuser.model';
import { User } from '../../modules/users/model/user.model';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user = new User('', '', '', '', '', '');
  message: string;
  constructor(
    private loginService: LoginService,
    private router: Router
  ) { 
  }

  ngOnInit() {
  }
  
  login(){
    this.message = '';
    this.loginService.login(this.user).subscribe((userAuthentication: CurrentUser) => {
      this.loginService.saveLoginInformations(userAuthentication);
      this.router.navigate(['xanana']);
      window.location.reload;
    }, err => {
      this.loginService.clearUserSessionStorage();
      this.message = 'ERROR';
    });
  }

  getFromGroupClass(isInvalid: boolean,  isDirty): {}{
    return {
      'form-group' : true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    }
  }
  

}
