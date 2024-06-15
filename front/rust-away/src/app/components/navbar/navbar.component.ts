import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { UserNavbarComponent } from '../user-navbar/user-navbar.component';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [RouterModule, UserNavbarComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  isNavMenuOpen = false;
  isTogglerOpen = false;
  isLogged;
  
  constructor(protected service: LoginService, 
    private router: Router) {
      this.isLogged = false
    }

  ngOnInit(): void {
    this.isLogged = this.service.isAuth()
  }

  get login() {
    return this.service.loginInfo;
  }

  logOut() {
    this.service.logout();
    this.router.navigate(['/login'])
  }

  togglerClick(): void {
    this.isTogglerOpen = !this.isTogglerOpen;
    this.isNavMenuOpen = !this.isNavMenuOpen;
  }

  navLinkClick(): void {
    if (this.isNavMenuOpen) {
      this.togglerClick();
    }
  }

}
