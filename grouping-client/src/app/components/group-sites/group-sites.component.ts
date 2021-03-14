import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Groups } from 'src/app/models/groups';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { NewSiteComponent } from '../new-site/new-site.component';

@Component({
  selector: 'app-group-sites',
  templateUrl: './group-sites.component.html',
  styleUrls: ['./group-sites.component.css']
})
export class GroupSitesComponent implements OnInit {

  @Input() groupId!: number;

  siteWithReviewList: any[] = [];

  constructor(
    public edgeService: EdgeService,
    private router: Router,
    private newSiteDialog: MatDialog,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) {
    //Get the group id from the route
    this.groupId = Number(this.route.snapshot.paramMap.get('groupId'));
  }

  ngOnInit(): void {
    //console.log(this.groupId);

    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);

    //If the group id is 1, redirect to home page
    } else if (this.groupId === 1) {
      this.router.navigate(['/home']);

    } else {
      //Get the sites of a group
      this.getSitesByGroupId(this.groupId);
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  //Get the sites of a group
  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      this.siteWithReviewList = result;

      //Reset the site list
      this.edgeService.siteList = [];

      //Fill the site list with the sites, but without the review's mean
      result.forEach(element => {
        this.edgeService.siteList.push(new Site(element.id, element.name, element.mapUrl, ''));
      });

    //If there is an error, it may be because the user hasn't acces to the group
    }, error => {
      this._snackBar.open("You don't have access to this group", '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
      this.router.navigate(['/groups']);
    });
  }

  //Function to open the new site dialog to create a new one
  openNewSiteDialog(): void {
    let dialogRef = this.newSiteDialog.open(NewSiteComponent, {
      height: '600px',
      width: '700px',
      hasBackdrop: true,
      disableClose: true
    });
    
    //Send the site list of the group
    dialogRef.componentInstance.siteGroupList = this.edgeService.siteList;

    //When close, execute ngOnInit function to load the new site
    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit();}, 1000);
    });
  }

}
