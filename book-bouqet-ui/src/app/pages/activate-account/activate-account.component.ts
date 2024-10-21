import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {


  message: string = '';
  isOkay: boolean = false;
  submitted: boolean = false;


  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ) {
  }

  onCodeCompleted(token: string) {
    this.confirmAccountActivation(token);
  }

  confirmAccountActivation(token: string) {
    this.authService.activateAccount(
      {
        code: token
      }
    ).subscribe(
      {
        next: () => {
          this.message = 'Your account has been activated successfully.\nNow you can continue to login';
          this.submitted = true;
          this.isOkay = true;
        },
        error: (err) => {
          console.log(err);
          this.message = 'Code has been expired or invalid. Please try again.';
          this.submitted = true;
          this.isOkay = false;
        }
      }
    )
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }
}
