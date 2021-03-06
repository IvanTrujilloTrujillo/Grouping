import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Groups } from 'src/app/models/groups';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.css']
})
export class NewReviewComponent implements OnInit {

  groupList: Groups[] = [];

  reviewForm: FormGroup;

  nameField: FormControl;
  groupField: FormControl;
  mapUrlField: FormControl;

  constructor(
    private edgeService: EdgeService
  ) {
    this.nameField = new FormControl('', [Validators.required]);
    this.groupField = new FormControl('', [Validators.required]);
    this.mapUrlField = new FormControl('', []);

    this.reviewForm = new FormGroup({
      name: this.nameField,
      group: this.groupField,
      mapUrl: this.mapUrlField
    });
  }

  ngOnInit(): void {
    this.getAllGroups();
  }

  getAllGroups(): void {
    this.edgeService.getAllGroups().subscribe(result => {
      this.groupList = result;
    });
  }

  onSubmit(): void {
    
  }

}
