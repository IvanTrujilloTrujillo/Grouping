import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  center!: any;
  zoom = 17;
  display?: google.maps.LatLngLiteral;

  constructor(
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<MapComponent>,
    private router: Router
  ) {}

  ngOnInit(): void {

    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {

      //Get the user id from the tocken
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
      
      //console.log(this.center);
    }
  }

  close(): void {
    this.dialogRef.close();
  }

}
