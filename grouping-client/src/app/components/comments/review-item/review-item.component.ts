import { Component, Input, OnInit } from '@angular/core';
import { Review } from 'src/app/models/review';
import { ReviewWithUserName } from 'src/app/models/review-with-user-name';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-review-item',
  templateUrl: './review-item.component.html',
  styleUrls: ['./review-item.component.css']
})
export class ReviewItemComponent implements OnInit {

  @Input() review!: ReviewWithUserName;

  userName: string = '';

  constructor(
    public edgeService: EdgeService
  ) {}

  ngOnInit(): void {
  }

}
