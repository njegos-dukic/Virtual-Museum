import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Museum } from '../../interfaces/museum';

const httpOptions = {
  headers: new HttpHeaders({
    'clientId': 'username',
    'clientPassword': 'password'
  })
}

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  private apiURL = 'http://localhost:9000/museums';

  constructor(private http: HttpClient) { }

  getMuseums(): Observable<Museum[]> {
    return this.http.get<Museum[]>(this.apiURL);
  }

  getMuseumById(id: number): Observable<Museum> {
    let apiURLById: string = `${this.apiURL}/${id}`;
    return this.http.get<Museum>(apiURLById, httpOptions);
  }
}