import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'clientId': 'username',
    'clientPassword': 'password'
  })
}

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private countriesNowAPI = 'https://countriesnow.space/api/v0.1/countries/cities';

  constructor(private http: HttpClient) { }

  private getCitiesForCountry(country: string): Observable<any> {
    return this.http.post<any>(this.countriesNowAPI, { "country": country });
  }

  getWeatherForCities(country: string): any[] {
    let result: any[] = [];
    let cities: any[] = [];
    this.getCitiesForCountry(country).subscribe(c => {
      const shuffled = c.data.sort(() => 0.5 - Math.random());
      cities = shuffled.slice(0, 3);
      cities.forEach(city => {
        let openWeatherAPI = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=faf5aa13ee038bef51339c8d22859850&units=metric`
        this.http.get<any>(openWeatherAPI).subscribe(weather => {
          result.push(weather)
        });
      });
    });
    return result;
  }
}