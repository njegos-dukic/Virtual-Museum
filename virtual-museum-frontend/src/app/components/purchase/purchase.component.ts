import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DetailedTour } from '../../interfaces/detailedTour';
import { TourService } from '../../services/tour/tour.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

  tourId!: any;
  tour!: DetailedTour;

  constructor(private activatedRoute: ActivatedRoute, private tourService: TourService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.tourId = params.get('tourId');
      this.tourService.getTourById(parseInt(this.tourId)).subscribe(tour => { this.tour = tour; console.log("TOUR: ", tour); });
    });
  }
}
