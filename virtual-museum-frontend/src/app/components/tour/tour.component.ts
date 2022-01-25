import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Museum } from 'src/app/interfaces/museum';
import { MuseumService } from '../../services/museum/museum.service';
import { Tour } from '../../interfaces/tour';
import { TourService } from '../../services/tour/tour.service';
import { WeatherService } from '../../services/weather/weather.service';
import { DetailedTour } from 'src/app/interfaces/detailedTour';
import { Artifact } from 'src/app/interfaces/artifact';
import { ArtifactService } from 'src/app/services/artifacts/artifact.service';
import { DomSanitizer } from '@angular/platform-browser';
import { TourRequest } from 'src/app/interfaces/tourRequest';

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.css']
})
export class TourComponent implements OnInit {

  constructor(private artifactService: ArtifactService, private router: Router, private activatedRoute: ActivatedRoute, private museumService: MuseumService, private tourService: TourService, private domSanitizer: DomSanitizer) { }

  tour!: DetailedTour;
  tourId!: any;
  artifacts!: Artifact[];

  ticketNumber: string = '';
  error!: string;
  success!: string;

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => { this.tourId = params.get('tourId');
    this.tourService.getTourById(+this.tourId).subscribe(result => {
      this.tour = result;
      this.tour.start = new Date(result.start);
    },
      err => this.router.navigate(['login']));
    });
  }

  isImage(artifact: Artifact): boolean {
    return artifact.type == 'img';
  }

  isVideo(artifact: Artifact): boolean {
    return artifact.type == 'vid';
  }

  isYoutube(artifact: Artifact): boolean {
    return artifact.type == 'ytube';
  }

  getArtifacts() {
    this.artifacts = [];
    this.error = '';
    this.success = '';
    if (this.ticketNumber.length < 64) {
      this.error = 'Please input ticket number.';
      return;
    }

    let tourRequest: TourRequest = {
      ticketNumber: this.ticketNumber.replace(/\s+/g, ''),
      userId: +(localStorage.getItem('id')!)
    }

    this.success = 'Request is processing. Waiting for response.'
    this.artifactService.getArtifacts(this.tourId, tourRequest).subscribe(artifacts => {
      this.success = 'Artifacts successfully collected. See below';
      this.error = '';
      artifacts.forEach(a => { 
        if(this.isImage(a)) 
          a.base64 = this.domSanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,' + a.base64);
        else if (this.isVideo(a))
          a.base64 = this.domSanitizer.bypassSecurityTrustResourceUrl('data:video/mp4;base64,' + a.base64);
      });
      this.artifacts = artifacts;
    }, err => { this.success = ''; this.error = 'Invalid ticket number for this tour.'; });
  }
}