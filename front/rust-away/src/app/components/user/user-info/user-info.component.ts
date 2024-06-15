import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { FormsModule } from '@angular/forms';
import { AuthComponent } from '../../auth/auth.component';
import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-info',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent implements OnInit {

  user:User;

  constructor(private loginService : LoginService, 
    private userService: UserService,
    private router: Router) {
    this.user = new User();
  }


  ngOnInit(): void {
    this.getUserInfo();
  }

  editUserInfo() {
    this.router.navigate(['/edit-user']);
  }


  private getUserInfo() {
    const username = this.loginService.getUsername();
    this.userService.getUserByUsername(username).subscribe({
      next: response => {
        this.user = response;
      },
      error: error => {
        if (error.status == 500) {
          Swal.fire('Error al recuperar la información del usuario', 'Inténtelo de nuevo más tarde', 'error')
        } else {
          throw error;
        }
        console.log(error)
      }
    });
  }
  // TODO: Llamada al servicio para pedir el usuario por username
}
