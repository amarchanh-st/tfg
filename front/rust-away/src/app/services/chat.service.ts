import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private url: string = 'http://localhost:8080/chats/';

  constructor(private http: HttpClient) { }

  sendMessage(budgetId:number, chat:any) {
    return this.http.put<any>(this.url + "addChat/" +budgetId ,chat)
  }
}