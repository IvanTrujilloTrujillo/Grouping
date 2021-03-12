import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { User } from 'src/app/models/user';
import { EdgeService } from 'src/app/services/edge.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  usernameField: FormControl;
  passwordField: FormControl;

  constructor(
    private edgeService: EdgeService,
    private router: Router,
    private _snackBar: MatSnackBar
    ) {
      this.usernameField = new FormControl('', [Validators.required]);
      this.passwordField = new FormControl('', [Validators.required]);
      this.loginForm = new FormGroup({
        username: this.usernameField,
        password: this.passwordField
      });
    }

  ngOnInit(): void {
  }

  login(): void {
    const user: User = new User(1, '', this.usernameField.value, this.passwordField.value);
    this.edgeService.login(user).subscribe(result => {
      this.edgeService.tocken = result.tocken;
      localStorage.setItem('tockenLogin', result.tocken);
      this.edgeService.userId = Number(result.tocken.substr(0, 4));
      this.router.navigate(['/home']);
      this._snackBar.open('Successful login', '', {
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    });
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
