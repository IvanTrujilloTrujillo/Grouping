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
  @Input() mean!: number;

  constructor(
    public app: AppComponent,
    private newReviewDialog: MatDialog,
    private mapDialog: MatDialog,
    private router: Router,
    private edgeService: EdgeService
  ) { }

  ngOnInit(): void {
  }

  showNewReviewDialog(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});
    this.newReviewDialog.open(NewReviewComponent);
  }

  openMapDialog(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});
    let dialogRef = this.mapDialog.open(MapComponent, { data: {site: this.site}});
    const center = {lat: Number(this.site.mapUrl.split("@")[1].split(",")[0]), lng: Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0])};
    dialogRef.componentInstance.center = center;
  }

  seeReviews(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, "")});
    console.log(new Site(this.site.id, this.site.name, this.site.mapUrl, ""));
    console.log(this.edgeService.siteList);
    console.log(this.edgeService.selectedSiteId);
    this.router.navigate(['/comments']);
  }
}
