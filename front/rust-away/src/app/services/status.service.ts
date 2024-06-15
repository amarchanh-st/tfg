import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  private url: string = 'http://localhost:8080/status/';

  constructor(private http: HttpClient) { }

  updateStatus(budgetId:number, status:string) {
    return this.http.put<any>(this.url + "update/" +budgetId +"?newStatus="+status,null)
  }
}
