import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Museum } from 'src/app/interfaces/museum';
import { MuseumService } from '../../services/museum/museum.service';
import { Tour } from '../../interfaces/tour';
import { TourService } from '../../services/tour/tour.service';
import { WeatherService } from '../../services/weather/weather.service';


@Component({
  selector: 'app-museum',
  templateUrl: './museum.component.html',
  styleUrls: ['./museum.component.css']
})
export class MuseumComponent implements OnInit {

  museumId!: any;
  upcomingTours: Tour[] = [];
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private museumService: MuseumService, private tourService: TourService, private weatherService: WeatherService) { }

  museum!: Museum;

  cities: any[] = [];
  weather: any[] = [];

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.museumId = params.get('museumId');
      this.museumService.getMuseumById(parseInt(this.museumId)).subscribe(museum => { 
        this.museum = museum
        this.weather = this.weatherService.getWeatherForCities(museum.country);
      },
      err => this.router.navigate(['login']))});
      this.tourService.getUpcomingToursForMuseum(parseInt(this.museumId)).subscribe(tours => {
        this.upcomingTours = tours;
        this.upcomingTours.forEach(t => {
          t.start = new Date(t.start);
          this.museumService.getMuseumById(t.museumId).subscribe(museum => t.museumName = museum.name, err => this.router.navigate(['login'])
        );
      })
    });
  }

  buyTicket(tourId: number): void {
    this.router.navigate([`purchase/${tourId}`]);
  }
}
