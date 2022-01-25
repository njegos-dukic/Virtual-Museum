import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DetailedTour } from '../../interfaces/detailedTour';
import { TourService } from '../../services/tour/tour.service';
import { Router } from '@angular/router';
import { PurchasePost } from 'src/app/interfaces/purchasePost';
import { PurchaseService } from 'src/app/services/purchase/purchase.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

  tourId!: any;
  tour!: DetailedTour;

  firstName!: string;
  lastName!: string;
  cardType!: string;
  cardNumber!: string;
  cvv!: string;
  email!: string;
  expirationDate!: string;

  error!: string;
  success!: string;

  constructor(private activatedRoute: ActivatedRoute, private tourService: TourService, private router: Router, private purchaseService: PurchaseService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.tourId = params.get('tourId');
      this.tourService.getTourById(parseInt(this.tourId)).subscribe(tour => { this.tour = tour; },
        err => console.log(err));
    });
  }

  purchase() {

    this.error = '';
    this.success = '';

    let purchasePost: PurchasePost = {
      firstName: this.firstName,
      lastName: this.lastName,
      cardType: this.cardType,
      cardNumber: this.cardNumber,
      expirationDate: this.expirationDate,
      cvv: this.cvv,
      email: this.email,
      tourId: this.tourId,
      userId: +(localStorage.getItem('id')!)
    }

    if (this.firstName.length < 3 || this.lastName.length < 3 || this.cardType.length < 4 || this.cardNumber.length != 16 || this.cvv.length != 3 || this.email.length < 5 || this.expirationDate.length != 4) {
      this.error = 'Please enter information in valid format.';
      return;
    }

    this.success = 'Processing request and sending ticket to email. Please wait.';

    this.purchaseService.purchaseTicket(purchasePost).subscribe(result => { this.success = 'Ticket successfully bought.'; this.error = ''; }, err => { this.error = 'Error while purchasing ticket.'; this.success = ''; });
  }
}
