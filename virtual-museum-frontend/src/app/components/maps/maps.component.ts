import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent {

  @Input() lat!: number;
  @Input() lng!: number;
}