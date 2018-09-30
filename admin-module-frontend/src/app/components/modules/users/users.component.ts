import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';
import { ResponseApi } from '../../../model/response-api.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  totalUsers: string = '0';
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getTotalOfUsers();
  }

  getTotalOfUsers(){
    this.userService.getTotalUsers().subscribe((responseApi: ResponseApi) => {
      this.totalUsers = responseApi.data;
    })
  }

}
