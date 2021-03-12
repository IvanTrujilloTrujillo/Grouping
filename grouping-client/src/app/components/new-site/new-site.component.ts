import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Review } from 'src/app/models/review';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';
import { NewReviewComponent } from '../new-review/new-review.component';

@Component({
  selector: 'app-new-site',
  templateUrl: './new-site.component.html',
  styleUrls: ['./new-site.component.css']
})
export class NewSiteComponent implements OnInit {

  siteGroupList!: Site[];

  createNewSite: boolean = false;
  siteList: Site[] = [];

  siteForm: FormGroup;

  selectSiteField: FormControl;
  nameField: FormControl;
  mapUrlField: FormControl;
  ratingField: FormControl;
  commentField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewSiteComponent>,
    private router: Router
  ) {
    this.selectSiteField = new FormControl('', []);
    this.nameField = new FormControl('', [Validators.required]);
    this.mapUrlField = new FormControl('', []);
    this.ratingField = new FormControl('', [Validators.required]);
    this.commentField = new FormControl('', []);

    this.siteForm = new FormGroup({
      selectSite: this.selectSiteField,
      name: this.nameField,
      mapUrl: this.mapUrlField,
      rating: this.ratingField,
      comment: this.commentField
    });
  }

  ngOnInit(): void {
    if (this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
      this.edgeService.getAllSites().subscribe(result => {
        this.siteList = result;
        this.siteGroupList.forEach(element => {
          let indexToDelete = -1;
          for (let index = 0; index < this.siteList.length; index++) {
            const element2 = this.siteList[index];
            if (element.id === element2.id) {
              indexToDelete = index;
              break;
            }
          }
          if (indexToDelete !== -1) {
            this.siteList.splice(indexToDelete, 1);
          }
        });
      });
    }
  }

  onSubmit(): void {
    let name: string = this.nameField.value;
    let mapUrl: string = this.mapUrlField.value;

    name = name.trim();

    if(mapUrl === null || mapUrl === undefined) {
      mapUrl = '';
    }

    //Check if the site already exists
    this.edgeService.siteList.forEach(site => {
      if(site.name === name || site.mapUrl === mapUrl) {
        alert("This site already exists");
        return;
      }
    });

    if(this.ratingField.value === '') {
      alert("The rating is required");
      return;
    }

    if(this.selectSiteField.value !== '' && !this.createNewSite) {
      name = this.selectSiteField.value.name;
      mapUrl = this.selectSiteField.value.mapUrl;
    } else if (this.selectSiteField.value !==  '') {
      alert("You haven't selected any site");
      return;
    }

    const site = new Site(1, name, mapUrl, this.edgeService.tocken);
    this.edgeService.saveNewSite(site).subscribe(result => {
      const review = new Review(1, this.edgeService.selectedGroup, result, 1, this.ratingField.value, this.commentField.value, this.edgeService.tocken);
      this.edgeService.saveNewReview(review).subscribe(result => {
      });
    });

    this.closeDialog();
  }

  closeDialog() {
    this.createNewSite = false;
    this.dialogRef.close('New Site created!');
  }

  cancel(): void {
    this.createNewSite = false;
    this.dialogRef.close();
  }

  newSite(): void {
    this.createNewSite = true;
  }

}
