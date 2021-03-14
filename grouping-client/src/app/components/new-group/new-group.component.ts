import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Groups } from 'src/app/models/groups';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-new-group',
  templateUrl: './new-group.component.html',
  styleUrls: ['./new-group.component.css']
})
export class NewGroupComponent implements OnInit {

  groupForm: FormGroup;

  nameField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private dialogRef: MatDialogRef<NewGroupComponent>,
    private router: Router
  ) {
    this.nameField = new FormControl('', [Validators.required]);

    this.groupForm = new FormGroup({
      name: this.nameField,
    });
  }

  ngOnInit(): void {

    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {

      //Get the user id from the tocken
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  //Create a new group
  onSubmit(): void {
    let name: string = this.nameField.value;

    name = name.trim();

    const group: Groups = new Groups(1, name, 0, this.edgeService.tocken);

    this.edgeService.saveNewGroup(group).subscribe(result => {
      this.edgeService.groupList.push(result);
    });
    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Group created!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

}
