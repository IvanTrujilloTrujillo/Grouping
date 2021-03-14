import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css']
})
export class JoinGroupComponent implements OnInit {

  joinGroupForm: FormGroup;

  codeField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    private dialogRef: MatDialogRef<JoinGroupComponent>,
    private _snackBar: MatSnackBar
  ) {
    this.codeField = new FormControl('', [Validators.required]);

    this.joinGroupForm = new FormGroup({
      code: this.codeField,
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

  onSubmit(): void {
    let code: string = this.codeField.value;

    code = code.trim();

    //Check if the user is already in the group
    this.edgeService.groupList.forEach(group => {
      if(group.id === (Number(code.split('#')[1]) - 247) / 34) {
        this._snackBar.open("You are already in this group", '', {
          duration: 5000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
        this.closeDialog();
        return;
      }
    });

    this.edgeService.joinGroup(code).subscribe(result => {
      this.edgeService.groupList.push(result);
    }, error => {

      //If there is an error is because the code isn't valid
      this._snackBar.open("This code isn't valid", '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    });

    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Group joined!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

}
