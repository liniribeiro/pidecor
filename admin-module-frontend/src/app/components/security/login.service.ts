import { Injectable } from '@angular/core';
import { CurrentUser } from '../modules/users/model/curentuser.model';
import { User } from '../modules/users/model/user.model';
import { LOCALHOST } from '../../services/consts.api';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class LoginService {

  token: string;
  currentUser = new CurrentUser();

  constructor(private http: HttpClient) { }

  saveLoginInformations(userAuthentication: CurrentUser) { 
      sessionStorage.setItem('id_token', userAuthentication.token);
      sessionStorage.setItem('user', JSON.stringify(userAuthentication.user));
      sessionStorage.setItem('profile', JSON.stringify(userAuthentication.user.profile));
  }

  login(user: User) {
    return this.http.post(`${LOCALHOST}/api/auth`, user);
  }

  public logout() {
    this.clearUserSessionStorage();
  };

  clearUserSessionStorage() {
    sessionStorage.removeItem('id_token');
    sessionStorage.removeItem('profile');
    sessionStorage.removeItem('user');
  }

  isLogedIn(): boolean {
    this.token = sessionStorage.getItem('id_token');
    return this.token != null;
  }

  getToken(): string {
    this.token = sessionStorage.getItem('id_token');
    return this.token;
  }

}
