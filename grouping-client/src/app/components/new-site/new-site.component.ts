import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Site } from 'src/app/models/site';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-new-site',
  templateUrl: './new-site.component.html',
  styleUrls: ['./new-site.component.css']
})
export class NewSiteComponent implements OnInit {

  @Input() groupId!: number;

  @Output() siteAddedEvent = new EventEmitter();

  siteForm: FormGroup;

  nameField: FormControl;
  mapUrlField: FormControl;

  constructor(
    private edgeService: EdgeService
  ) {
    this.nameField = new FormControl('', [Validators.required]);
    this.mapUrlField = new FormControl('', []);

    this.siteForm = new FormGroup({
      name: this.nameField,
      mapUrl: this.mapUrlField
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    let name: string = this.nameField.value;
    let mapUrl: string = this.mapUrlField.value;

    name = name.trim();
    //name = name.charAt(0).toUpperCase() + name.slice(1).toLowerCase().

    if(mapUrl === null || mapUrl === undefined) {
      mapUrl = '';
    }

    const site: Site = new Site(1, name, mapUrl);

    //TODO: Comprobar si es realmente un sitio nuevo

    this.edgeService.saveNewSite(site).subscribe(result => {
      this.siteAddedEvent.emit(result);
    });
  }

}
