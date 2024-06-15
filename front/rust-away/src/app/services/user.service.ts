import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { UserEdit } from '../models/user-edit';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = 'http://localhost:8080/user/';

  constructor(private http: HttpClient) { }

  signup(user: User) {
    return this.http.post<any>(this.url + "sign-up", user);
  }

  getUserByUsername(username:string) {
    return this.http.get<any>(this.url + username);
  }

  updateUser(username:string, user:UserEdit) {
    return this.http.put<any>(this.url +"edit/"+ username, user);
  }
}
