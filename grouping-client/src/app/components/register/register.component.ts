import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { User } from 'src/app/models/user';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  nameField: FormControl;
  usernameField: FormControl;
  passwordField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {
    this.nameField = new FormControl('', [Validators.required]);
    this.usernameField = new FormControl('', [Validators.required]);
    this.passwordField = new FormControl('', [Validators.required]);
    this.registerForm = new FormGroup({
      name: this.nameField,
      username: this.usernameField,
      password: this.passwordField
    });
  }

  ngOnInit(): void {
  }

  register() {

    //Create the new user
    const user: User = new User(1, this.nameField.value, this.usernameField.value, this.passwordField.value);

    this.edgeService.register(user).subscribe(result => {

      //Save the tocken on local storage
      localStorage.setItem('tockenLogin', result.tocken);

      //Gets the tocken with the credentials
      this.edgeService.tocken = result.tocken;

      //Get the user id from the tocken
      this.edgeService.userId = Number(result.tocken.substr(0, 4));

      //Go to home page
      this.router.navigate(['/home']);

      //Show a succesful message
      this._snackBar.open('Successful registration', '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    }, error => {

      //If there is an error it's because the username already exists
      this._snackBar.open("The username already exists", '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    });
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}
