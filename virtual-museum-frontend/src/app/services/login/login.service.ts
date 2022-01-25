import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from 'src/app/interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiURL = 'http://localhost:9000/auth/login';

  constructor(private httpClient: HttpClient) { }

  login(username: string, password: string): Observable<User> {
    const httpOptions = {
      headers: new HttpHeaders({
        'clientId': username,
        'clientPassword': password
      })
    }
    return this.httpClient.get<User>(this.apiURL, httpOptions);
  }

  logout(username: string, password: string): Observable<any> {
    let logoutApiURL = 'http://localhost:9000/auth/logout';
    const httpOptions = {
      headers: new HttpHeaders({
        'clientId': username,
        'clientPassword': password,
        'skip': 'true'
      })
    }

    return this.httpClient.get<any>(logoutApiURL, httpOptions);
  }

  loggedIn(): boolean {
    return (localStorage.getItem('loggedIn') === '1');
  }

  isAdmin(): boolean {
    return (localStorage.getItem('adminToken') != null && localStorage.getItem('adminToken') != 'null' && localStorage.getItem('adminToken') != '');
  }
}
