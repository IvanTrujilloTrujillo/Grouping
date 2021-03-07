import { Component } from '@angular/core';
import { Groups } from './models/groups';
import { Site } from './models/site';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'grouping-client';

  public groupList: Groups[] = [];
  public siteList: Site[] = [];

  public selectedGroup: number = 1;
  public selectedSiteId: number = 0;
}
