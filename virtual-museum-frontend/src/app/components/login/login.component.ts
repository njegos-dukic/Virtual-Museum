import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username!: string;
  public password!: string;
  public error!: string;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void { }

  login(username: string, password: string) {
    this.error = '';
    let user = { 'username': username, 'password': password };
    localStorage.setItem('user', JSON.stringify(user));
    this.loginService.login(username, password).subscribe(result => { localStorage.setItem('loggedIn', '1'); localStorage.setItem('id', result.id.toString()); localStorage.setItem('adminToken', result.adminToken); localStorage.setItem('user', JSON.stringify(result)); this.router.navigate(['home']); }, err => { this.error = 'Invalid credentials.'; });
  }
}