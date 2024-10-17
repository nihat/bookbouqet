import {Component} from '@angular/core';
import {AuthenticationRequest} from '../../services/models/authentication-request';
import {AuthenticationService} from '../../services/services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: string[] = [];

  constructor(private router: Router,
              private authenticationService: AuthenticationService
              //implement another service
  ) {
  }

  login() {
    this.errorMsg = [];
    this.authenticationService.authenticate(
      {
        body: this.authRequest
      }
    ).subscribe(
      {
        next: (res) => {
          this.router.navigate(['books']);
        }, error: (err) => {
          console.log(err);
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error.message);
          }
        }
      }
    )
  }

  register() {
    this.router.navigate(['register']);
  }
}
