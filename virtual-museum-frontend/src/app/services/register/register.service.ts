import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { RegistrationPost } from 'src/app/interfaces/registrationPost';
import { User } from 'src/app/interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiURL = 'http://localhost:9000/auth/register';

  constructor(private http: HttpClient) { }

  register(registrationInfo: RegistrationPost): Observable<User> {
    const httpOptions = {
      headers: new HttpHeaders({
        "skip": "1"
      })
    }
    return this.http.post<User>(this.apiURL, registrationInfo, httpOptions);
  }
}
