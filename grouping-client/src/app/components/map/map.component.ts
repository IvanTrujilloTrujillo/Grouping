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

  public latitude: number;
  public longitude: number;

  center: any;
  zoom = 15;
  display?: google.maps.LatLngLiteral;

  constructor(
    @Inject(MAT_DIALOG_DATA) public site: Site,
    private app: AppComponent,
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<MapComponent>,
    private router: Router
  ) {
    console.log(this.site);
    let mySite: Site = new Site(this.site.id, this.site.name, this.site.mapUrl, this.site.tocken);
    console.log(mySite);
    this.latitude = Number(this.site.mapUrl.split("@")[1].split(",")[0]);
    this.longitude = Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0]);
    this.center = {lat: this.latitude, lng: this.longitude};
    console.log(this.latitude);
    console.log(this.longitude);
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.app.userId = Number(this.edgeService.tocken.substr(0, 4));
      this.latitude = Number(this.site.mapUrl.split("@")[1].split(",")[0]);
      this.longitude = Number(this.site.mapUrl.split("@")[1].split(",")[1].split(",")[0]);
      //console.log(this.site.mapUrl);
      //console.log(this.latitude);
      //console.log(this.longitude);
    }
  }

  close(): void {
    this.dialogRef.close();
  }

}
