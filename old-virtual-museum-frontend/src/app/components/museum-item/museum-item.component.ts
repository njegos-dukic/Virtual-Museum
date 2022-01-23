import { Component, OnInit, Input } from '@angular/core';
import { Museum } from '../../Museum';
import { faCalendarDay } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-museum-item',
  templateUrl: './museum-item.component.html',
  styleUrls: ['./museum-item.component.css']
})
export class MuseumItemComponent implements OnInit {

  @Input() museum!: Museum;
  faCalendarDay = faCalendarDay;

  constructor() { }

  ngOnInit(): void {
  }

}
