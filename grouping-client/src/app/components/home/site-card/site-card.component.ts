import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AppComponent } from 'src/app/app.component';
import { Site } from 'src/app/models/site';
import { NewReviewComponent } from '../../new-review/new-review.component';

@Component({
  selector: 'app-site-card',
  templateUrl: './site-card.component.html',
  styleUrls: ['./site-card.component.css']
})
export class SiteCardComponent implements OnInit {

  @Input() site!: Site;
  @Input() groupId!: number;

  constructor(
    public app: AppComponent,
    private newReviewDialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  showNewReviewDialog(): void {
    this.app.selectedSiteId = this.site.id;
    this.newReviewDialog.open(NewReviewComponent);
  }

  openMapDialog(): void {
    
  }
}
