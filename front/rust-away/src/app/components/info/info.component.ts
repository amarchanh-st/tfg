import { Component } from '@angular/core';

@Component({
  selector: 'app-info',
  standalone: true,
  imports: [],
  templateUrl: './info.component.html',
  styleUrl: './info.component.css'
})
export class InfoComponent {
  workshop = {
    name: 'Rust Away',
    address: 'Rambla del Poblenou, 154-156, Sant Martí, 08018 Barcelona',
    phone: '+34 555 555 555',
    latitude: 38.983333, // Replace with actual latitude
    longitude: -3.916667 // Replace with actual longitude
  }; // Workshop data property

  car= 'assets/car.png';

  constructor() { }

  openGoogleMapsDirections(): void {
    const directionsUrl = `https://www.google.com/maps/dir/?api=1&destination=${this.workshop.address}`; // Replace with actual placeId if available
    window.open(directionsUrl, '_blank');
  }

}
