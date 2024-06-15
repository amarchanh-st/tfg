import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';
import Swal from 'sweetalert2';
import { UserEdit } from '../../../models/user-edit';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent implements OnInit{

  user: UserEdit;
  passwordCheck:string;

  constructor(private loginService: LoginService, 
    private userService: UserService,
    private router: Router
  ) {
    this.user = new UserEdit();
    this.passwordCheck="";
  }

  ngOnInit(): void {
    this.user.username = this.loginService.getUsername();
  }

  onSubmit() {
    // TODO: Check all fields
    if(this.passwordCheck !== this.user.newPassword) {
      Swal.fire('Error', 'Las contraseñas deben ser idénticas', 'error');
    }
    else {
      console.log(this.user)
      const username = this.loginService.getUsername();
      this.userService.updateUser(username, this.user).subscribe({
        next: response => {
          this.router.navigate(['/user-info']);
        },
        error: error => {
          if (error.status == 500) {
            Swal.fire('Error al editar el usuario', 'Inténtelo de nuevo más tarde', 'error')
          } else {
            throw error;
          }
          console.log(error)
        }
      });
    }
  }
}
