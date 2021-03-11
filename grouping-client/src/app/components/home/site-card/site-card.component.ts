import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { MapComponent } from '../../map/map.component';
import { NewReviewComponent } from '../../new-review/new-review.component';

@Component({
  selector: 'app-site-card',
  templateUrl: './site-card.component.html',
  styleUrls: ['./site-card.component.css']
})
export class SiteCardComponent implements OnInit {

  @Input() site!: Site;
  @Input() groupId!: number;

  rating: number = 1;

  constructor(
    public app: AppComponent,
    private newReviewDialog: MatDialog,
    private mapDialog: MatDialog,
    private router: Router,
    private edgeService: EdgeService
  ) { }

  ngOnInit(): void {
    this.meanReviews();
    console.log(this.rating);
  }

  meanReviews(): void {
    this.edgeService.meanReviews(this.groupId, this.site).subscribe(result => {
      console.log(result);
      this.rating = result;
    });
  }

  showNewReviewDialog(): void {
    this.app.selectedSiteId = this.app.siteList.findIndex(site => {return site === this.site});
    this.newReviewDialog.open(NewReviewComponent);
  }

  openMapDialog(): void {
    this.app.selectedSiteId = this.app.siteList.findIndex(site => {return site === this.site});
    let dialogRef = this.mapDialog.open(MapComponent, { data: {site: this.site}});
    //dialogRef.componentInstance.site = this.site;
    dialogRef.componentInstance.latitude = Number(this.site.mapUrl.split("@")[1].split(",")[0]);
    dialogRef.componentInstance.longitude = Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0]);
  }

  seeReviews(): void {
    this.app.selectedSiteId = this.app.siteList.findIndex(site => {return site === this.site});
    console.log(this.site);
    console.log(this.app.selectedSiteId);
    this.router.navigate(['/comments']);
  }
}
