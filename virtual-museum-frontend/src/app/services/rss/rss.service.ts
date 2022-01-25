import { Injectable } from '@angular/core';
import { HttpClient, HttpBackend } from '@angular/common/http'
import { Observable } from 'rxjs';
import { RootObject } from '../../interfaces/huffPostRSS'

@Injectable({
  providedIn: 'root'
})
export class RssService {

  private apiURL = 'https://api.rss2json.com/v1/api.json?rss_url=http://www.huffpost.com/section/arts/feed';

  private http!: HttpClient;

  constructor(private httpBackend: HttpBackend) { 
    this.http = new HttpClient(httpBackend);
  }

  getNews(): Observable<RootObject> {
    return this.http.get<RootObject>(this.apiURL);
  }
}
