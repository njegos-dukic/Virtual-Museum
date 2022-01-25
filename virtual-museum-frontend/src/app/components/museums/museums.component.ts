import { Component, OnInit } from '@angular/core';
import { Museum } from '../../interfaces/museum';
import { MuseumService } from '../../services/museum/museum.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css']
})
export class MuseumsComponent implements OnInit {

  museums: Museum[] = [];
  filteredMuseums: Museum[] = [];
  filterString: string = '';

  constructor(private museumService: MuseumService, private router: Router) { }

  ngOnInit(): void {
    this.museumService.getMuseums().subscribe((museums) => { this.museums = museums; this.filteredMuseums = museums; },
    err => this.router.navigate(['login']));
  }

  resetFilteredMuseums(): void {
    this.filteredMuseums = [];
    this.museums.forEach(museum => this.filteredMuseums.push(museum));
  }

  searchMuseums(filter: string): void {
    this.resetFilteredMuseums();
    if (filter.length !== 0 || filter !== null || filter !== '') {
      this.filteredMuseums = [];
      const museums: Museum[] = [];
      for (const museum of this.museums) {
        if (museum.name.toLowerCase().indexOf(filter.toLowerCase()) !== -1 || museum.city.toLowerCase().indexOf(filter.toLowerCase()) !== -1) {
          this.filteredMuseums.push(museum);
        }   
      }
    }
  }
}
