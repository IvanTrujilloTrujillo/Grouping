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
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.getSitesByGroupId(1);
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      //console.log(result);
      this.siteWithReviewList = result;
      this.edgeService.siteList = [];
      result.forEach(element => {
        this.edgeService.siteList.push(new Site(element.id, element.name, element.mapUrl, ''));
      });
    });
  }

  openNewSiteDialog(): void {
    let dialogRef = this.newSiteDialog.open(NewSiteComponent, {
      height: '600px',
      width: '700px',
      hasBackdrop: true,
      disableClose: true
    });
    dialogRef.componentInstance.siteGroupList = this.edgeService.siteList;

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit();}, 200);
    });
  }
}
