import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
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

  ratingField: FormControl;
  commentField: FormControl;

  constructor(
    public app: AppComponent,
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewReviewComponent>,
    private router: Router
  ) {
    this.ratingField = new FormControl('', [Validators.required]);
    this.commentField = new FormControl('', []);
    this.reviewForm = new FormGroup({
      rating: this.ratingField,
      comment: this.commentField
    });
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    }
  }


  onSubmit(): void {
    const review: Review = new Review(1,
      this.app.selectedGroup,
      this.app.siteList[this.app.selectedSiteId],
      1,
      Number(this.ratingField.value),
      this.commentField.value,
      this.edgeService.tocken
    );

    try{
      this.edgeService.saveNewReview(review).subscribe(result => {
      });
    } catch {
      alert("You has already sended a review previously");
    }

    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Review created!');
  }

}

