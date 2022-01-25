import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AgmCoreModule } from '@agm/core';
import { YouTubePlayerModule } from '@angular/youtube-player';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { MuseumsComponent } from './components/museums/museums.component';
import { ToursComponent } from './components/tours/tours.component';
import { HomeComponent } from './components/home/home.component';
import { MuseumComponent } from './components/museum/museum.component';
import { PurchaseComponent } from './components/purchase/purchase.component';
import { AuthGuard } from './guards/auth.guard';
import { CredentialsInterceptorService } from './services/interceptor/credentials-interceptor.service';
import { MapsComponent } from './components/maps/maps.component';
import { TourComponent } from './components/tour/tour.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    MuseumsComponent,
    ToursComponent,
    HomeComponent,
    MuseumComponent,
    PurchaseComponent,
    MapsComponent,
    TourComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCN2ri-N8fFuE2KEdSdBU40Nxd39vkbY28'
    }),
    YouTubePlayerModule
  ],
  providers: [AuthGuard, 
  {
    provide: HTTP_INTERCEPTORS,
    useClass: CredentialsInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
