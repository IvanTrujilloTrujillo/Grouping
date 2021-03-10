import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupSitesComponent } from './group-sites.component';

describe('GroupSitesComponent', () => {
  let component: GroupSitesComponent;
  let fixture: ComponentFixture<GroupSitesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupSitesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupSitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
