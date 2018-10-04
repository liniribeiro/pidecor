import { Component, OnInit } from '@angular/core';
import { ResponseApi } from '../../../../model/response-api.model';
import { UserService } from '../user.service';

declare interface TableData {
  headerRow: string[];
  dataRows: string[][];
}

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  pages: Array<number>;
  listUser = [];
  message = {};
  classCss = {};
  headerConfig = [{title: 'Id', attribute: "id"}, 
                  {title: 'Nome', attribute: "name"}, 
                  {title: 'Email', attribute: "email"},
                  {title: 'Papel', attribute: "roleDescription"}]


  constructor(private userService: UserService) { }

  public tableData1: TableData;
  ngOnInit() {
    this.findAll();
  }

  getTableOfUsers(): TableData {
    this.tableData1 = {
      headerRow: this.headerConfig.map((config) => config.title),
      dataRows: this.listUser.map((user) => {
        return this.headerConfig.map((config) => user[config.attribute])
      })
    };
    
    return  this.tableData1;
  }

  findAll() {
    this.userService.findAll().subscribe((responseApi: ResponseApi) => {
      this.listUser = responseApi.data;
    }, err => {
     
    });
  }

  private showMessage(message: { type: string, text: string }): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-' + type] = true;
  }

  deleteUser(row: any) {
    var id: string = row[0];
    this.userService.delete(id).subscribe((responseApi: ResponseApi) => {
      this.showMessage({
        type: 'sucess',
        text: 'Usuário excluído com sucesso!'
      });
      this.findAll();
      window.location.reload();
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

}
