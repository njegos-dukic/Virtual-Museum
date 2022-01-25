import { Component, OnInit } from '@angular/core';
import { RootObject, Item, Enclosure, Feed } from '../../interfaces/huffPostRSS';
import { RssService } from '../../services/rss/rss.service';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  rss!: RootObject;

  constructor(private rssService: RssService, private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    let user = JSON.parse(localStorage.getItem('user') || '{}');
    let username = user.username;
    let password = user.password;
    if (username === null || password === null)
      this.router.navigate(['login']);
    this.loginService.login(username, password).subscribe(result => {}, err => this.router.navigate(['login']));
    this.rssService.getNews().subscribe(news => this.rss = news);
  }
}
