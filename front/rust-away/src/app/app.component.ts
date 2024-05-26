import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RustAwayComponent } from './components/rust-away.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RustAwayComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'rust-away';
}
