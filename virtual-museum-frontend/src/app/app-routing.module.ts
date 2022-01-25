import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MuseumComponent } from './components/museum/museum.component';
import { MuseumsComponent } from './components/museums/museums.component';
import { PurchaseComponent } from './components/purchase/purchase.component';
import { RegisterComponent } from './components/register/register.component';
import { TourComponent } from './components/tour/tour.component';
import { ToursComponent } from './components/tours/tours.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'purchase/:tourId',
    component: PurchaseComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'tour/:tourId',
    component: TourComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'museums',
    component: MuseumsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'museum/:museumId',
    component: MuseumComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'tours',
    component: ToursComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
