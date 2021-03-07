import { Component, OnInit } from '@angular/core';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  siteList: Site[] = [];

  newSiteAdded: boolean = false;
  newSite: Site = new Site(1, '', '');

  constructor(
    private edgeService: EdgeService
  ) { }

  ngOnInit(): void {
    this.getSitesByGroupId(1);
  }

  getSitesByGroupId(id: number): void {
    this.edgeService.getSitesByGroupId(id).subscribe(result => {
      this.siteList = result;
    });
  }

  addNewSiteToList(site: Site): void {
    this.siteList.push(site);
    this.newSite = site;
    this.newSiteAdded = true;

  }

  closeNewReviewApp() {
    this.newSiteAdded = false;
    this.newSite = new Site(1, '', '');
  }

}
