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
    this.groupId = Number(this.route.snapshot.paramMap.get('groupId'));
    this.siteId = Number(this.route.snapshot.paramMap.get('siteId'));
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      if(this.edgeService.selectedSiteId = 0) {
        this._snackBar.open("There is no selected site", '', {
          duration: 5000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
        this.router.navigate(['/home']);
      } else {
        this.site = this.edgeService.siteList.find(element => element.id === this.siteId);
        if (this.site !== undefined) {
          this.chargeReviews(this.groupId, this.site);
        } else {
          this.router.navigate(['/home']);
        }
      }
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  chargeReviews(groupId: number, site: any): void {
    //console.log(site);
    //console.log(groupId);
    this.edgeService.chargeReviews(groupId, site).subscribe(result => {
      this.reviewList = result;
      //console.log(result);
    });
  }

}
