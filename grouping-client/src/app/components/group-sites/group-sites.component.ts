import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Groups } from 'src/app/models/groups';
import { EdgeService } from 'src/app/services/edge.service';
import { NewSiteComponent } from '../new-site/new-site.component';

@Component({
  selector: 'app-group-sites',
  templateUrl: './group-sites.component.html',
  styleUrls: ['./group-sites.component.css']
})
export class GroupSitesComponent implements OnInit {

  @Input() groupId!: number;

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    public app: AppComponent,
    private newSiteDialog: MatDialog,
    private route: ActivatedRoute
  ) {

    this.groupId = Number(this.route.snapshot.paramMap.get('groupId'));
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.getSitesByGroupId(this.groupId);
      this.app.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      this.app.siteList = result;
    }, error => {
      alert("You don't have access to this group");
      this.router.navigate(['/groups']);
    });
  }

  openNewSiteDialog(): void {
    const dialogRef = this.newSiteDialog.open(NewSiteComponent);

    dialogRef.afterClosed().subscribe(result => {

    });
  }

}
