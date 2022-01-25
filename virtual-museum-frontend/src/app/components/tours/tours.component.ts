import { Component, OnInit } from '@angular/core';
import { Tour } from '../../interfaces/tour';
import { TourService } from '../../services/tour/tour.service';
import { MuseumService } from '../../services/museum/museum.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tours',
  templateUrl: './tours.component.html',
  styleUrls: ['./tours.component.css']
})
export class ToursComponent implements OnInit {

  tours: Tour[] = [];
  filteredTours: Tour[] = [];
  filterString: string = '';

  constructor(private tourService: TourService, private museumService: MuseumService, private router: Router) { }

  ngOnInit(): void {
    this.tourService.getCurrentTours().subscribe((tours) => { 
      this.tours = tours;
      this.tours.forEach(t => {
        t.start = new Date(t.start);
        this.museumService.getMuseumById(t.museumId).subscribe(museum => t.museumName = museum.name);
      }, 
      this.filteredTours = tours); 
    },
    err => this.router.navigate(['login']));
  }

  resetFilteredTours(): void {
    this.filteredTours = [];
    this.tours.forEach(tour => this.filteredTours.push(tour));
  }

  searchTours(filter: string): void {
    this.resetFilteredTours();
    if (filter.length !== 0 || filter !== null || filter !== '') {
      this.filteredTours = [];
      const tours: Tour[] = [];
      for (const tour of this.tours) {
        if (tour.name.toLowerCase().indexOf(filter.toLowerCase()) !== -1 || tour.museumName.toLowerCase().indexOf(filter.toLowerCase()) !== -1) {
            this.filteredTours.push(tour);
        }
      }   
    }
  }
}