import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private url: string = 'http://localhost:8080/api/user/sign-up';

  constructor(private http: HttpClient) { }

  signup(user: User) {
    return this.http.post<User>(this.url, user);
  }
}
