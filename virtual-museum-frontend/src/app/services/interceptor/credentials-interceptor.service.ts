import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CredentialsInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: any, next: any): any {
    if (req.headers.get("skip"))
      return next.handle(req);

    let modifiedRequest = req.clone({
      setHeaders: {
        clientId: JSON.parse(localStorage.getItem('user') || '{}')?.username,
        clientPassword: JSON.parse(localStorage.getItem('user') || '{}')?.password
      }
    });
    return next.handle(modifiedRequest);
  }
}
