import { Component } from '@angular/core';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MdbCarouselModule],
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
  /*  
  this.photoService.getImages().then((images) => {
        this.images = images;
    });
    */
   this.images = ["https://i0.wp.com/css-tricks.com/wp-content/uploads/2022/07/angular-url-shortener-featured-image.png?fit=1280%2C720&ssl=1",
   "https://i0.wp.com/css-tricks.com/wp-content/uploads/2019/12/angular-logo.png?fit=1200%2C600&ssl=1&resize=350%2C200"
   ];
}

}
