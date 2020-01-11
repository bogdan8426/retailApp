import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllProductsComponent } from './display-all-products.component';

describe('DisplayAllProductsComponent', () => {
  let component: DisplayAllProductsComponent;
  let fixture: ComponentFixture<DisplayAllProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayAllProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAllProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
