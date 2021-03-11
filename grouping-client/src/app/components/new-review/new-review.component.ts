import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Review } from 'src/app/models/review';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { HomeComponent } from '../home/home.component';

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
    } else {
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }


  onSubmit(): void {
    let review: Review = new Review(1,
      this.edgeService.selectedGroup,
      this.edgeService.newSite,
      1,
      Number(this.ratingField.value),
      this.commentField.value,
      this.edgeService.tocken
    );

    if(this.edgeService.newSite.name !== '') {
      this.edgeService.saveNewSite(this.edgeService.newSite).subscribe(result => {
        review.site = result;
        this.edgeService.siteList.push(result);
        console.log(this.edgeService.siteList);
        this.edgeService.newSite = new Site(1, '', '', '');
      });
    }

    try{
      console.log(review);
      setTimeout(() => {
        this.edgeService.saveNewReview(review).subscribe(result => {
          console.log(review);
        });
      }, 100);
    } catch {
      alert("You has already sended a review previously");
    }

    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Review created!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

}

