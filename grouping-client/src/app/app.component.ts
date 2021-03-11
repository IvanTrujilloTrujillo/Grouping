import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { Groups } from './models/groups';
import { Site } from './models/site';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'grouping-client';

  constructor(
  ) {}

}
