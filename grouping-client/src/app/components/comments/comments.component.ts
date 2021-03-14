import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Review } from 'src/app/models/review';
import { ReviewWithUserName } from 'src/app/models/review-with-user-name';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input() groupId!: number;
  @Input() siteId!: number;

  site: any;

  reviewList: ReviewWithUserName[] = [];

  constructor(
    public edgeService: EdgeService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) {
    //Get the group and site ids from the route
    this.groupId = Number(this.route.snapshot.paramMap.get('groupId'));
    this.siteId = Number(this.route.snapshot.paramMap.get('siteId'));
  }

  ngOnInit(): void {

    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {

      //If there is no site is (for example, if reload the page), redirect to home page
      if(this.edgeService.selectedSiteId = 0) {
        this._snackBar.open("There is no selected site", '', {
          duration: 5000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
        this.router.navigate(['/home']);
      } else {

        //Get the site object from the site id
        this.site = this.edgeService.siteList.find(element => element.id === this.siteId);

        //If it's on the list, charge all the reviews, if not, redirect to home page
        if (this.site !== undefined) {
          this.chargeReviews(this.groupId, this.site);
        } else {
          this.router.navigate(['/home']);
        }
      }

      //Get the user id from the tocken
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  //Get all the reviews from a site
  chargeReviews(groupId: number, site: any): void {
    //console.log(site);
    //console.log(groupId);

    this.edgeService.chargeReviews(groupId, site).subscribe(result => {
      this.reviewList = result;
      //console.log(result);
    });
  }

}
