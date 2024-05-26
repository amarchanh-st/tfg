import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Login } from '../../models/login';
import { RouterModule } from '@angular/router';
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
export class AuthComponent {

  login: Login;

  constructor(
    private sharingData: SharingDataService) {
    this.login = new Login();
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
