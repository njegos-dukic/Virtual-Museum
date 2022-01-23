import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RootObject } from '../../interfaces/huffPostRSS'
import { HtmlParser } from '@angular/compiler';

const httpOptions = {
  headers: new HttpHeaders({
    'accept': 'text/xml',
    'clientId': 'username',
    'clientPassword': 'password'
  })
}

@Injectable({
  providedIn: 'root'
})
export class RssService {

  private apiURL = 'http://www.huffpost.com/section/arts/feed';

  constructor(private http: HttpClient) { }

  getNews(): Observable<RootObject> {
    return this.http.get<RootObject>(this.apiURL, httpOptions);
  }
}
