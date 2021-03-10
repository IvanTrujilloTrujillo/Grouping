import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Review } from 'src/app/models/review';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  reviewList: Review[] = [];

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    public app: AppComponent
  ) { }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      if(this.app.selectedSiteId = 0) {
        alert("There is no selected site");
        this.router.navigate(['/home']);
      } else {
        this.chargeReviews(this.app.siteList[this.app.selectedSiteId], this.app.selectedGroup);
      }
      this.app.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  chargeReviews(site: Site, groupId: number): void {
    this.edgeService.chargeReviews(site, groupId).subscribe(result => {
      this.reviewList = result;
    });
  }

}
