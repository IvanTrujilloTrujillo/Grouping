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

    this.groupId = Number(this.route.snapshot.paramMap.get('groupId'));
  }

  ngOnInit(): void {
    //console.log(this.groupId);
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else if (this.groupId === 1) {
      this.router.navigate(['/home']);
    } else {
      this.getSitesByGroupId(this.groupId);
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      this.siteWithReviewList = result;
      this.edgeService.siteList = [];
      result.forEach(element => {
        this.edgeService.siteList.push(new Site(element.id, element.name, element.mapUrl, ''));
      });
    }, error => {
      this._snackBar.open("You don't have access to this group", '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
      this.router.navigate(['/groups']);
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
