import { Component } from '@angular/core';
import { User } from '../../models/user';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';
import { SignupService } from '../../services/signup.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  user : User;

  constructor(
    private service: SignupService
  ) {
    this.user = new User();
  }

  onSubmit() {
    if (!this.user.username || !this.user.password  || !this.user.name  || !this.user.surname) {
      Swal.fire(
        'Error en la creación de cuenta',
        'Debes rellenar todos los campos para crear una cuenta',
        'error'
      );
    } else {
      // TODO: Call to service
      console.log(this.user);
    }
  }

}
