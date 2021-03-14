import { Component, OnInit } from '@angular/core';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { MatDialog } from '@angular/material/dialog';
import { NewSiteComponent } from '../new-site/new-site.component';
import { AppComponent } from 'src/app/app.component';
import { Router } from '@angular/router';
import { SiteWithReview } from 'src/app/models/site-with-review';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  siteWithReviewList: any[] = [];

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    private newSiteDialog: MatDialog
  ) { }

  ngOnInit(): void {
    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {

      //Get the sites of the Global group
      this.getSitesByGroupId(1);

      //Get the user id from the tocken
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  //Get the sites of a group
  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      //console.log(result);

      this.siteWithReviewList = result;

      //Reset the site list
      this.edgeService.siteList = [];

      result.forEach(element => {
        //Fill the site list with the site, but without the Reviews' mean
        this.edgeService.siteList.push(new Site(element.id, element.name, element.mapUrl, ''));
      });
    });
  }

  //Function to open a dialog to create a new site
  openNewSiteDialog(): void {
    let dialogRef = this.newSiteDialog.open(NewSiteComponent, {
      height: '600px',
      width: '700px',
      hasBackdrop: true,
      disableClose: true
    });

    //Send the site list of the group
    dialogRef.componentInstance.siteGroupList = this.edgeService.siteList;

    //After close, reload the site list to add the new one
    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit();}, 1000);
    });
  }
}
