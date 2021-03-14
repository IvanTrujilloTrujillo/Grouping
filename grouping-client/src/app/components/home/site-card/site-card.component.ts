import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { GroupSitesComponent } from '../../group-sites/group-sites.component';
import { MapComponent } from '../../map/map.component';
import { NewReviewComponent } from '../../new-review/new-review.component';
import { HomeComponent } from '../home.component';

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
    private edgeService: EdgeService,
    private home: HomeComponent,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    //console.log(this.mean);
  }


  //Function to open a dialog to write a new review
  showNewReviewDialog(): void {
    //Get the site id of the selected one
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});
    let dialogRef = this.newReviewDialog.open(NewReviewComponent, {
      hasBackdrop: true,
      disableClose: true
    });
    //Send the selected site to the dialog
    dialogRef.componentInstance.site = this.site;

    //After close, reload the site list to show the new mean of the site
    dialogRef.afterClosed().subscribe(result => {
      if(this.groupId === 1) {
        setTimeout(() => {this.home.ngOnInit();}, 1000);
      } else {
        //setTimeout(() => {this.groupSites.ngOnInit();}, 1000);
      }
    });
  }

  //Function to open a dialog to see the Google Maps of the site
  openMapDialog(): void {
    //Get the site id of the selected one
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, '')});

    //Check if the mapUrl is a Google Maps url
    if(this.site.mapUrl.includes("@")) {

      let dialogRef = this.mapDialog.open(MapComponent, {
        data: {site: this.site},
        hasBackdrop: true,
        disableClose: true
      });

      //Get the coordinates of the site in Google Maps
      const center = {lat: Number(this.site.mapUrl.split("@")[1].split(",")[0]), lng: Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0])};

      //Send the coordinates to the dialog
      dialogRef.componentInstance.center = center;

    } else {
      //If isn't a valid url, show a error message
      this._snackBar.open('The map is not available for this site', '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    }
  }

  //Function to see all the reviews and comments of a site
  seeReviews(): void {
    //Get the site id of the selected one
    this.edgeService.selectedSiteId = this.edgeService.siteList.findIndex(site => {return site === new Site(this.site.id, this.site.name, this.site.mapUrl, "")});

    //console.log(new Site(this.site.id, this.site.name, this.site.mapUrl, ""));
    //console.log(this.edgeService.siteList);
    //console.log(this.edgeService.selectedSiteId);

    this.router.navigate(['/group/' + this.groupId + '/site/' + this.site.id + '/comments']);
  }
}
