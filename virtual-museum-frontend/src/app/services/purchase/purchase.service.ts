import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PurchasePost } from 'src/app/interfaces/purchasePost';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  private apiURL = 'http://localhost:9000/virtual-bank'

  constructor(private http: HttpClient) { }

  purchaseTicket(purchasePost: PurchasePost): Observable<any> {
    return this.http.post<any>(this.apiURL, purchasePost);
  }
}
