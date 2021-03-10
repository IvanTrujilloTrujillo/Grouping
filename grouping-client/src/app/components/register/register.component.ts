import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
    private app: AppComponent,
    private router: Router
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
    const user: User = new User(1, this.nameField.value, this.usernameField.value, this.passwordField.value);
    this.edgeService.register(user).subscribe(result => {
      localStorage.setItem('tockenLogin', result.tocken);
      this.edgeService.tocken = result.tocken;
      this.app.userId = Number(result.tocken.substr(0, 4));
      this.router.navigate(['/home']);
    });
  }
}