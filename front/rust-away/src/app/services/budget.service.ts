import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Budget } from '../models/budget';
import { NewBudget } from '../models/newBudget';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {
  private url: string = 'http://localhost:8080/budget/';

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<any>(this.url+ "find-all");
  }

  findById(id:number) {
    return this.http.get<any>(this.url+ id);
  }

  newBudget(budget:NewBudget){
    return this.http.post<any>(this.url +"new", budget);
  }

  updatePrice(budgetId:number, price:any) {
    return this.http.put<any>(this.url + "addPrice/" +budgetId, price);
  }

}
