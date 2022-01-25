import { Component, OnInit } from '@angular/core';
import { RegistrationPost } from 'src/app/interfaces/registrationPost';
import { RegisterService } from 'src/app/services/register/register.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName!: string;
  lastName!: string;
  username!: string;
  email!: string;
  password!: string;
  rePassword!: string;
  error!: string;

  constructor(private registerService: RegisterService, private router: Router) { }

  ngOnInit(): void { }

  hasLowerCase(str: string) {
    return str.toUpperCase() != str;
  }

  hasUpperCase(str: string) {
    return str.toLowerCase() != str;
  }

  hasNumber(str: string) {
    return /\d/.test(str);
  }

  register(): void {
    if (this.password !== this.rePassword) {
      this.error = 'Please enter the same passwords.';
      return;
    }

    if (this.password.length < 15) {
      this.error = 'Password must be at least 15 characters long.';
      return;
    }

    if (this.username.length < 12) {
      this.error = 'Username must be at least 12 characters long.';
      return;
    }

    if (this.username.includes('@') || this.username.includes('#') || this.username.includes('/')) {
      this.error = 'Username can\'t have the following chars: @, #, /.';
      return;
    }

    if (!this.hasLowerCase(this.password) || !this.hasUpperCase(this.password) || !this.hasNumber(this.password)) {
      this.error = 'Password must have upper case letter, lower case letter as well as a digit.';
      return;
    }

    let registrationDTO: RegistrationPost = {
      "firstName": this.firstName,
      "lastName": this.lastName,
      "userName": this.username,
      "email": this.email,
      "password": this.password
    }

    this.registerService.register(registrationDTO).subscribe(user => { 
      localStorage.setItem('loggedIn', '1'); 
      localStorage.setItem('adminToken', user.adminToken); 
      localStorage.setItem('user', JSON.stringify(user)); 
      this.router.navigate(['home']); 
    },
    err => { console.log(err); this.error = 'Error creating account.'; });
  }
}
