import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from '../services/login.service';
import { SharingDataService } from '../services/sharing-data.service';
import Swal from 'sweetalert2';
import { FooterComponent } from './footer/footer.component';

@Component({
  selector: 'app-rust-away',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent],
  templateUrl: './rust-away.component.html',
  styleUrl: './rust-away.component.css'
})
export class RustAwayComponent implements OnInit {

  constructor( private router: Router,
    private loginService: LoginService,
    private sharingData: SharingDataService) {}


  ngOnInit(): void {
    this.handlerLogin();
    this.handlerSignUp();
  }

  handlerLogin() {
    this.sharingData.handlerLoginEventEmitter.subscribe(({ username, password }) => {
      this.loginService.login({ username, password }).subscribe({
        next: response => {
          const token = response.token;
          const payload = this.loginService.getPayload(token);

          const user = { username: payload.sub };
          const login = {
            user,
            isAuth: true,
            role: payload.ROLE
          };
          
          this.saveUserInfoAndRedirect(token, login);
        },
        error: error => {
          if (error.status == 500) {
            Swal.fire('Error en el Login', 'Inténtelo de nuevo más tarde', 'error')
          } else {
            throw error;
          }
        }
      })
    })
  }

  handlerSignUp() {
    this.sharingData.handlerSingUpEventEmitter.subscribe((token) => {
      const payload = this.loginService.getPayload(token.token);
      const user = { username: payload.sub };
      const login = {
        user,
        isAuth: true,
        role: payload.ROLE
      };
      
      this.saveUserInfoAndRedirect(token.token, login);
    })
  }

  // Private method to avoid code duplication
  private saveUserInfoAndRedirect(token:any ,login:any) {
    this.loginService.token = token;
    this.loginService.loginInfo = login;
    this.router.navigate(['/home']);
  }

}
