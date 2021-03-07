import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Groups } from 'src/app/models/groups';
import { Review } from 'src/app/models/review';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.css']
})
export class NewReviewComponent implements OnInit {

  @Input() site!: Site;
  @Input() groupId!: number;

  @Output() reviewAddedEvent = new EventEmitter();

  reviewForm: FormGroup;

  ratingField: FormControl;
  commentField: FormControl;

  constructor(
    private edgeService: EdgeService
  ) {
    this.ratingField = new FormControl('', [Validators.required]);
    this.commentField = new FormControl('', []);

    this.reviewForm = new FormGroup({
      rating: this.ratingField,
      comment: this.commentField
    });
  }

  ngOnInit(): void {
  }


  onSubmit(): void {
    const review: Review = new Review(1,
      this.groupId,
      this.site,
      1,
      Number(this.ratingField.value),
      this.commentField.value
    );

    this.edgeService.saveNewReview(review).subscribe(result => {
      this.reviewAddedEvent.emit('');
    });
  }

}

