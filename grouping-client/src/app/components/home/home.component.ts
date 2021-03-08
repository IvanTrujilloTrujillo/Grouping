import { Component, OnInit } from '@angular/core';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { MatDialog } from '@angular/material/dialog';
import { NewSiteComponent } from '../new-site/new-site.component';
import { AppComponent } from 'src/app/app.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    public app: AppComponent,
    private edgeService: EdgeService,
    private router: Router,
    private newSiteDialog: MatDialog
  ) { }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.getSitesByGroupId(1);
    }
  }

  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      this.app.siteList = result;
    });
  }

  openNewSiteDialog(): void {
    this.newSiteDialog.open(NewSiteComponent);
  }
}
