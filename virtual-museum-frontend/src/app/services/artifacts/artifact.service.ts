import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artifact } from 'src/app/interfaces/artifact';
import { TourRequest } from 'src/app/interfaces/tourRequest';

@Injectable({
  providedIn: 'root'
})
export class ArtifactService {

  private apiURL = 'http://localhost:9000/artifacts/'

  constructor(private http: HttpClient) { }

  getArtifacts(tourId: string, tourRequest: TourRequest): Observable<Artifact[]> {
    return this.http.post<Artifact[]>(this.apiURL + tourId, tourRequest);
  }
}
