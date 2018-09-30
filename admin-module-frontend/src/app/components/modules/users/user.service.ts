import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LOCALHOST } from '../../../services/consts.api';
import { Observable } from 'rxjs/Observable';
import { User } from './model/user.model';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  createOrUpdate(user: User) {
    if (user.id != null && user.id != '') {
      return this.http.put(`${LOCALHOST}/api/user`, user);
    } else {
      user.id = null;
      return this.http.post(`${LOCALHOST}/api/user`, user);
    }
  }

  getUsers(page: number, count: number): Observable<User[]> {
    return this.http.get<User[]>(`${LOCALHOST}/api/user/${page}/${count}`);
  }
  
  findAll() {
    return this.http.get(`${LOCALHOST}/api/user/all`);
  }

  findById(id: string) {
    return this.http.get(`${LOCALHOST}/api/user/${id}`);
  }

  delete(id: string) {
    return this.http.delete(`${LOCALHOST}/api/user/${id}`);
  }

  getTotalByProfile() {
    return this.http.get(`${LOCALHOST}/api/user/getTotalByProfile`);
  }

  getTotalUsers() {
    return this.http.get(`${LOCALHOST}/api/user/totalUsers`);
  }
}