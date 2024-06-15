import { Component } from '@angular/core';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { LoginService } from '../../services/login.service';
import { HomeScrollComponent } from '../../home-scroll/home-scroll.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MdbCarouselModule, HomeScrollComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
  images: any[] | undefined;

  responsiveOptions: any[] = [
    {
        breakpoint: '1024px',
        numVisible: 5
    },
    {
        breakpoint: '768px',
        numVisible: 3
    },
    {
        breakpoint: '560px',
        numVisible: 1
    }
];

constructor() {}

ngOnInit() {
   this.images = ["https://www.telegraph.co.uk/cars/images/2016/10/03/BMT-main_trans_NvBQzQNjv4Bq-MpaW5mhZ3p2x8lezorrwCwlC6iyZV0F0yuJxTR1KzY.jpg?imwidth=680",
   "https://images.ctfassets.net/uc0w7zwkmx6m/5rNduiC0wvgVROqtXyQQyt/7e0f246e96e60f78a71625dfecf62c1a/GAB_2628.jpg",
   "https://spicercollectorcars.com/wp-content/uploads/2019/08/A-Good-Car-Restoration-H2.jpg"
   ];
}

}
