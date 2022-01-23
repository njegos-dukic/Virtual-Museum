import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MuseumComponent } from './components/museum/museum.component';
import { MuseumsComponent } from './components/museums/museums.component';
import { PurchaseComponent } from './components/purchase/purchase.component';
import { RegisterComponent } from './components/register/register.component';
import { ToursComponent } from './components/tours/tours.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'purchase/:tourId',
    component: PurchaseComponent
  },
  {
    path: 'museums',
    component: MuseumsComponent
  },
  {
    path: 'museum/:museumId',
    component: MuseumComponent
  },
  {
    path: 'tours',
    component: ToursComponent
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
