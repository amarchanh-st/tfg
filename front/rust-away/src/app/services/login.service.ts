import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../models/login';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string = 'http://localhost:8080/user/log-in';

  // This variables will store user and token info to be accessed from application
  private _token: string | undefined;

  private _loginInfo: any = {
    user: undefined,
    isAuth: false,
    role: ""
  };

  constructor(private http: HttpClient) { }

  login(login: Login) : Observable<any>{
    return this.http.post<any>(this.url, login);
  }

  set loginInfo(login:any) {
    this._loginInfo = login;
    // We store login info (with user and role) in order to use in future tasks
    sessionStorage.setItem('login', JSON.stringify(login));
          
  }

  get loginInfo() {
    // If user is authenticated, we return the current memory object
    if(this._loginInfo.isAuth) {
      return this._loginInfo;
    }
    // If user reload the page, we try to retrieve object from sessionStorage
    else if (sessionStorage.getItem('login') != null) {
      this._loginInfo = JSON.parse(sessionStorage.getItem('login') || '{}');
      return this._loginInfo;
    }
    // Otherwise, we return the template object
    return this._loginInfo;
  }

  set token(token: string) {
    this._token = token;
    // We store token in order to use in future requests
    sessionStorage.setItem('token', token);
  }

  get token() {
    if(this._token != undefined) {
      return this._token;
    }
    else if (sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token') || "";
      return this._token;
    }
    return this._token!;
  }

  getPayload(token: string) {
    if(token != null) {
      return JSON.parse(atob(token.split(".")[1]));
    }
    else {
      return null;
    }
  } 

  isAdmin() {
    return this.loginInfo.role === "ROLE_ADMIN" || false;
  }

  isAuth() {
    return this.loginInfo.isAuth || false;
  }

  getRole() {
    return this.loginInfo.role;
  }

  logout() {
    this._token = undefined;
    this._loginInfo = {
      user: undefined,
      isAuth: false,
      role: ""
    };
    sessionStorage.removeItem('login');
    sessionStorage.removeItem('token');
  }

}
