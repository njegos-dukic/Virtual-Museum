import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'Virtual Museum';
  date: Date = new Date();
  adminToken!: string;

  constructor(private router: Router, public loginService: LoginService) { }

  goToPage(pageName: string){
    this.router.navigate([`${pageName}`]);
  }

  openAdminPage() {
    window.open(`http://localhost:8080/VirtualMuseumAdmin/jsp/Homepage.jsp?adminToken=${localStorage.getItem('adminToken')}`, "_blank");
  }

  logout() {
    let username = JSON.parse(localStorage.getItem('user') || '{}').username;
    let password = JSON.parse(localStorage.getItem('user') || '{}').password;

    this.loginService.logout(username, password).subscribe(result => { console.log("Log out: ", result); })
    localStorage.removeItem('user');
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('adminToken');
    this.router.navigate(['login']);
  }
}
