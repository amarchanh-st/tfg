import { Component } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-rust-away',
  standalone: true,
  imports: [NavbarComponent, RouterOutlet],
  templateUrl: './rust-away.component.html',
  styleUrl: './rust-away.component.css'
})
export class RustAwayComponent {

  constructor() {}


  //constructor(private photoService: PhotoService) {}



}
