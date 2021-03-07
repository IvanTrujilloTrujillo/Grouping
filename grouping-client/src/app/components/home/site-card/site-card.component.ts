import { Component, Input, OnInit } from '@angular/core';
import { Site } from 'src/app/models/site';

@Component({
  selector: 'app-site-card',
  templateUrl: './site-card.component.html',
  styleUrls: ['./site-card.component.css']
})
export class SiteCardComponent implements OnInit {

  @Input() site!: Site;
  @Input() groupId!: number;

  newReview: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  showNewReviewApp(): void {
    this.newReview = true;
  }

  closeNewReviewApp(): void {
    this.newReview = false;
  }

}
