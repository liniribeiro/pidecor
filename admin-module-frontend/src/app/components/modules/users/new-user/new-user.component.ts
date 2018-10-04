import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Router } from '../../../../../../node_modules/@angular/router';
import { User } from '../model/user.model';
import { NgForm } from '../../../../../../node_modules/@angular/forms';
import { ResponseApi } from '../../../../model/response-api.model';
import { ToastsManager } from '../../../../../../node_modules/ng2-toastr';
import { UserService } from '../user.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  user = new User('', '', '', '', '', '');
  message: {};
  classCss: {};

  constructor(
    private userService: UserService,
    private router: Router,
    public toastr: ToastsManager, vcr: ViewContainerRef) { 
      this.toastr.setRootViewContainerRef(vcr)
    }

  ngOnInit() {
  }

  register() {
    this.message = {};
    if (this.validateUser(this.user)){
      this.userService.createOrUpdate(this.user).subscribe((responseApi: ResponseApi) => {
        this.showSuccess();
        this.user = new User(null, '', '', '', '', '');
        this.form.resetForm();
        window.location.reload();
      }, err => {
        this.showError();
      });
    }
  }

  validateUser(user: User): boolean{   
    return user.email != null && user.name != null && user.profile != null && user.password!= null;
  }

  showSuccess() {
   // this.toastr.success('You are awesome!', 'Success!');
  }

  showError() {
  //  this.toastr.error('This is not good!', 'Oops!');
  }

  clear(){
    this.form.reset();
  }
  
}
