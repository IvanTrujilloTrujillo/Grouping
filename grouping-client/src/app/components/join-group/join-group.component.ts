import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
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
    private app: AppComponent,
    private dialogRef: MatDialogRef<JoinGroupComponent>,
  ) {
    this.codeField = new FormControl('', [Validators.required]);

    this.joinGroupForm = new FormGroup({
      code: this.codeField,
    });
  }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.app.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  onSubmit(): void {
    let code: string = this.codeField.value;

    code = code.trim();

    //Check if the user is already in the group
    this.app.groupList.forEach(group => {
      if(group.id === (Number(code.split('#')[1]) - 247) / 34) {
        alert("You are already in this group");
        this.closeDialog();
        return;
      }
    });

    try {
    this.edgeService.joinGroup(code).subscribe(result => {
      this.app.groupList.push(result);
    });
    } catch {
      alert("This code isn't valid");
    }
    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close('New Group joined!');
  }

  cancel(): void {
    this.dialogRef.close();
  }

}
