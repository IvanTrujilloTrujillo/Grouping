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
    private newReviewDialog: MatDialog,
    private mapDialog: MatDialog,
    private router: Router,
    private edgeService: EdgeService
  ) { }

  ngOnInit(): void {
  }

  showNewReviewDialog(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});
    let dialogRef = this.newReviewDialog.open(NewReviewComponent, {
      hasBackdrop: true,
      disableClose: true
    });
    dialogRef.componentInstance.site = this.site;
  }

  openMapDialog(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});
    if(this.site.mapUrl.includes("@")) {
      let dialogRef = this.mapDialog.open(MapComponent, {
        data: {site: this.site},
        hasBackdrop: true,
        disableClose: true
      });
      const center = {lat: Number(this.site.mapUrl.split("@")[1].split(",")[0]), lng: Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0])};
      dialogRef.componentInstance.center = center;
    } else {
      alert("The map is not available for this site");
    }
  }

  seeReviews(): void {
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, "")});
    //console.log(new Site(this.site.id, this.site.name, this.site.mapUrl, ""));
    //console.log(this.edgeService.siteList);
    //console.log(this.edgeService.selectedSiteId);
    this.router.navigate(['/group/' + this.groupId + '/site/' + this.site.id + '/comments']);
  }
}
