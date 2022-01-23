import { Component, OnInit } from '@angular/core';
import { Museum } from '../../Museum';
import { MuseumService } from '../../services/museum.service';

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css']
})

export class MuseumsComponent implements OnInit {

  museums: Museum[] = [];

  constructor(private museumService: MuseumService) { }

  ngOnInit(): void {
    this.museumService.getMuseums().subscribe((museums) => this.museums = museums);
  }
}