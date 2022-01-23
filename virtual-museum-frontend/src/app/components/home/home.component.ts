import { Component, OnInit } from '@angular/core';
import { RootObject, Item, Enclosure, Feed } from '../../interfaces/huffPostRSS';
import { RssService } from '../../services/rss/rss.service';
import * as xml2js from 'xml2js';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  rss!: RootObject;
  items: Item[] = [];

  constructor(private rssService: RssService) { }

  ngOnInit(): void {
    this.rssService.getNews().subscribe(root => {
      console.log("ROOT " + root);
     // xml2js.parseString(root, (err: any, res: RootObject) => {
       // this.rss = res;
      //}); 
    });
  }
}
