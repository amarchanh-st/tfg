import { Component } from '@angular/core';
import { User } from '../../../models/user';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';
import { SharingDataService } from '../../../services/sharing-data.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  user : User;
  conditionsFlag : boolean;

  constructor(
    private service: UserService,
    private router: Router,
    private sharingData: SharingDataService
  ) {
    this.user = new User();
    this.conditionsFlag = false;
  }

  onSubmit() {
    if (!this.user.username || !this.user.password  || !this.user.name  
      || !this.user.surname || !this.user.birthDate || !this.user.address) {
      Swal.fire(
        'Error en la creación de cuenta',
        'Debes rellenar todos los campos para crear una cuenta',
        'error'
      );
    } else {
      this.service.signup(this.user).subscribe({
        next: response => {
          this.sharingData.handlerSingUpEventEmitter.emit(response);
        },
        error: error => {
          if (error.status == 500) {
            Swal.fire('Error al crear el usuario', 'Inténtelo de nuevo más tarde', 'error')
          } else {
            throw error;
          }
        }
      });
    }
  }

  showConditions() {
    console.log(this.conditionsFlag)
    Swal.fire("Condicines de servicio", 
      "Al crear una cuenta, usted acepta los siguientes términos y condiciones: <br> <br>"+
"1. Uso del servicio: <br>"+
   "- Usted acepta utilizar el servicio de buena fe. <br>"+
   "- No está utilizará el servicio de forma ilegal <br>"+
   "- No infrinja los derechos de terceros. <br>"+
   "- Usted es responsable de todo el contenido que publica o comparte en el servicio. <br>"+
   "- Nos reservamos el derecho de eliminar cualquier contenido que consideremos inapropiado u ofensivo. <br> <br>"+
  "2. Privacidad: <br>"+
  "- Reconocemos que usted tiene derecho a la privacidad de sus datos personales. <br>"+
  "- Recopilamos y utilizamos sus datos personales únicamente con su consentimiento y de acuerdo con nuestra política de privacidad. <br>"+
  "- No compartiremos sus datos personales con terceros sin su consentimiento previo. <br> <br>"+
  "3. Propiedad intelectual: <br>"+
  "- Todo el contenido del servicio, incluyendo texto, imágenes, audio y vídeo, está protegido por derechos de autor y otras leyes de propiedad intelectual. <br>"+
"- Usted no puede utilizar ningún contenido del servicio sin nuestro consentimiento previo por escrito. <br>", 
      'info' )
  }

}
