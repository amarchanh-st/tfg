import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(protected service: LoginService, 
    private router: Router) {}

  get login() {
    return this.service.loginInfo;
  }

  logOut() {
    this.service.logout();
    this.router.navigate(['/login'])
  }

}
