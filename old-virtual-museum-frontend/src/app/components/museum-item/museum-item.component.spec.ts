import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumItemComponent } from './museum-item.component';

describe('MuseumItemComponent', () => {
  let component: MuseumItemComponent;
  let fixture: ComponentFixture<MuseumItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
