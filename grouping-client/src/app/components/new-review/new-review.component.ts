import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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

  @Input() site!: Site;

  reviewForm: FormGroup;

  rating: number = 5;
  commentField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewReviewComponent>,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {
    this.commentField = new FormControl('', []);
    this.reviewForm = new FormGroup({
      comment: this.commentField
    });
  }

  ngOnInit(): void {
    if (this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }


  onSubmit(): void {
    let review: Review = new Review(1,
      this.edgeService.selectedGroup,
      new Site(this.site.id, this.site.name, this.site.mapUrl, ''),
      this.edgeService.userId,
      Number(this.rating),
      this.commentField.value,
      this.edgeService.tocken
    );


    //console.log(review);
    setTimeout(() => {
      this.edgeService.saveNewReview(review).subscribe(result => {
        //console.log(review);
      }, error => {
        this._snackBar.open("You has already sended a review previously", '', {
          duration: 5000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
      });
    }, 100);

    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Review created!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

}

