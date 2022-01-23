import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Tour } from '../../interfaces/tour';
import { DetailedTour } from '../../interfaces/detailedTour';

const httpOptions = {
  headers: new HttpHeaders({
    'clientId': 'username',
    'clientPassword': 'password'
  })
}

@Injectable({
  providedIn: 'root'
})
export class TourService {

  private apiURL = 'http://localhost:9000/tours';

  constructor(private http: HttpClient) { }

  getTours(): Observable<Tour[]> {
    return this.http.get<Tour[]>(this.apiURL, httpOptions);
  }

  getTourById(id: number): Observable<DetailedTour> {
    let apiURLById: string = `${this.apiURL}/${id}`;
    return this.http.get<DetailedTour>(apiURLById, httpOptions);
  }

  getCurrentTours(): Observable<Tour[]> {
    let apiCurrentTours: string = `${this.apiURL}/current`;
    return this.http.get<Tour[]>(apiCurrentTours, httpOptions);
  }

  getUpcomingToursForMuseum(museumId: number): Observable<Tour[]> {
    let apiUpcomingTours: string = `${this.apiURL}/upcoming?museumId=${museumId}`;
    return this.http.get<Tour[]>(apiUpcomingTours, httpOptions);
  }
}
