import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
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

  selectedSite: number = -1;

  siteForm: FormGroup;

  selectSiteField: FormControl;
  nameField: FormControl;
  mapUrlField: FormControl;

  constructor(
    private app: AppComponent,
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewSiteComponent>,
    private newReviewDialog: MatDialog,
    private router: Router
  ) {
    this.selectSiteField = new FormControl('', []);
    this.nameField = new FormControl('', [Validators.required]);
    this.mapUrlField = new FormControl('', []);

    this.siteForm = new FormGroup({
      selectSite: this.selectSiteField,
      name: this.nameField,
      mapUrl: this.mapUrlField
    });
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
      this.edgeService.getAllSites().subscribe(result => {
        this.siteList = result;
        console.log(result);
        console.log(this.siteGroupList);
        console.log(this.edgeService.siteList);
        for (let index = 0; index < this.siteGroupList.length; index++) {
          let site = this.siteGroupList[index];
          if(this.siteList.includes(site)) {
            console.log(index);
            this.siteList.splice(index, 1);
            index--;
          }
        }
        console.log(this.siteList);
      });

      this.selectSiteField.statusChanges.subscribe(() => {
        if(this.selectedSite !== -1) {
          this.siteForm.controls['name'].setValue(this.siteList[this.selectedSite].name);
          this.siteForm.controls['mapUrl'].setValue(this.siteList[this.selectedSite].mapUrl);
        }
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

    this.edgeService.newSite = new Site(1, name, mapUrl, this.edgeService.tocken);

    this.closeDialog();
    this.openNewReviewDialog();
  }

  closeDialog() {
    this.createNewSite = false;
    this.dialogRef.close('New Site created!');
  }

  cancel(): void {
    this.createNewSite = false;
    this.dialogRef.close();
  }

  openNewReviewDialog(): void {
    this.newReviewDialog.open(NewReviewComponent);
  }

  newSite(): void {
    this.createNewSite = true;
  }

}
