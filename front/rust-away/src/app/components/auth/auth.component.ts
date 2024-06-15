import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Login } from '../../models/login';
import { Route, Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { SharingDataService } from '../../services/sharing-data.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent implements OnInit{

  login: Login;

  constructor(
    private sharingData: SharingDataService,
    private loginService: LoginService,
    private router: Router) {
    this.login = new Login();
  }

  ngOnInit(): void {
    // Check if the user is authenticated. If yes, redirects to homepage
    if(this.loginService.isAuth()) {
      this.router.navigate(['/home']);
    }
  }

  onSubmit() {
    if (!this.login.username || !this.login.password) {
      Swal.fire(
        'Login Error',
        'Debes indicar el nombre y la contraseña para iniciar sesión',
        'error'
      );
    } else {
      this.sharingData.handlerLoginEventEmitter.emit(this.login);
    }
  }

}
