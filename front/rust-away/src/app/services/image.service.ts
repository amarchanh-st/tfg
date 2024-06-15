import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private url: string = 'http://localhost:8080/images/';

  constructor(private http: HttpClient) { }

  uploadImages(budgetId:number, images:any) {
    return this.http.put<any>(this.url + "upload/" +budgetId ,images)
  }
}
