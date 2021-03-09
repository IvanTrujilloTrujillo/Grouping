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

  siteForm: FormGroup;

  nameField: FormControl;
  mapUrlField: FormControl;

  constructor(
    private app: AppComponent,
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewSiteComponent>,
    private newReviewDialog: MatDialog,
    private router: Router
  ) {
    this.nameField = new FormControl('', [Validators.required]);
    this.mapUrlField = new FormControl('', []);

    this.siteForm = new FormGroup({
      name: this.nameField,
      mapUrl: this.mapUrlField
    });
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    }
  }

  onSubmit(): void {
    let name: string = this.nameField.value;
    let mapUrl: string = this.mapUrlField.value;

    name = name.trim();

    if(mapUrl === null || mapUrl === undefined) {
      mapUrl = '';
    }

    const site: Site = new Site(1, name, mapUrl, this.edgeService.tocken);

    //TODO: Comprobar si es realmente un sitio nuevo

    this.edgeService.saveNewSite(site).subscribe(result => {
      this.app.siteList.push(result);
    });
    this.closeDialog();
    this.openNewReviewDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Site created!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

  openNewReviewDialog(): void {
    this.newReviewDialog.open(NewReviewComponent);
  }

}
