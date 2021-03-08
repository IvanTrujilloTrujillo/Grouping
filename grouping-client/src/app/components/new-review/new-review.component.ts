import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AppComponent } from 'src/app/app.component';
import { Review } from 'src/app/models/review';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.css']
})
export class NewReviewComponent implements OnInit {

  reviewForm: FormGroup;

  rate: number = 1;
  comment: string = '';

  constructor(
    public app: AppComponent,
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewReviewComponent>
  ) {
    this.reviewForm = new FormGroup({
    });
  }

  ngOnInit(): void {
  }


  onSubmit(): void {
    const review: Review = new Review(1,
      this.app.selectedGroup,
      this.app.siteList[this.app.selectedSiteId],
      1,
      Number(this.rate),
      this.comment
    );

    this.edgeService.saveNewReview(review).subscribe(result => {
      this.closeDialog();
    });
  }

  closeDialog() {
    this.dialogRef.close('New Review created!');
  }

}

