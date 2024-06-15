import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Budget } from '../../../models/budget';
import { BudgetService } from '../../../services/budget.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { Status } from '../../../models/status';
import { StatusService } from '../../../services/status.service';
import { CommonModule } from '@angular/common';
import { ImageService } from '../../../services/image.service';
import { ChatService } from '../../../services/chat.service';

@Component({
  selector: 'app-budget',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './budget.component.html',
  styleUrl: './budget.component.css'
})
export class BudgetComponent implements OnInit{

  budget : Budget;
  id:number;
  worker:boolean;
  statusList: Status[];
  selectedStatus = new Status();
  newMessage:string;
  selectedFile: File | null = null;
  imageUrl: any;
  @ViewChild('images') photo: ElementRef | undefined


	onSelected(value:any): void {
    this.selectedStatus = this.statusList[value.selectedIndex]
	}
 
 constructor(private service: BudgetService, 
    private route: ActivatedRoute,
    private loginService: LoginService,
    private statusService: StatusService,
    private imageService : ImageService,
    private chatService: ChatService) {

    this.budget = new Budget();
    this.id = 0;
    this.worker = false;
    this.newMessage = "";
    
    this.statusList = [
      {name:"Sin empezar",
        literal: "UNTOUCHED"}, 
      {name:"Revisado",
        literal:"REVIEWED"}, 
      {name:"En progreso",
        literal:"IN_PROGRESS"}, 
      {name:"Finalizado", 
        literal:"FINISHED"}]
  }

  ngOnInit(): void {
    this.worker = this.loginService.isAdmin();
   
    this.route.paramMap.subscribe(params => {
      this.id = + +(params.get('id') || '0') ;
    });    
    this.service.findById(this.id).subscribe({
      next: response => {
        this.budget = response;
        console.log(response)
        this.budget.price = ""+ this.formatPrice(this.budget.price!)
        console.log(this.budget.price)
      },
      error: error => {
        if (error.status == 500) {
          Swal.fire('Error al recuperar la información del presupuesto', 'Inténtelo de nuevo más tarde', 'error')
        } else {
          throw error;
        }
        console.log(error)
      }
    })
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];

      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageUrl = e.target?.result;
      };
      reader.readAsDataURL(this.selectedFile);
      
    }
  }

  uploadImage() {
    this.imageService.uploadImages(this.budget.id, {images: [this.imageUrl]}).subscribe({
      next: response => {
        this.budget = response;
        this.selectedFile = null
        this.imageUrl=null
        this.clearSelectedPhoto()
      },
      error: error => {
        if (error.status == 500) {
          Swal.fire('Error al recuperar la información del presupuesto', 'Inténtelo de nuevo más tarde', 'error')
        } else {
          throw error;
        }
        console.log(error)
      }
    })
  }

  sendMessage() {
    this.chatService.sendMessage(this.budget.id, {message: this.newMessage, 'sender':this.loginService.getUsername()}).subscribe({
      next: response => {
        this.budget = response;
        this.newMessage = ""
      },
      error: error => {
        if (error.status == 500) {
          Swal.fire('Error al recuperar la información del presupuesto', 'Inténtelo de nuevo más tarde', 'error')
        } else {
          throw error;
        }
        console.log(error)
      }
    })
  }
  
  updatePrice() {
    this.service.updatePrice(this.budget.id, {price:this.budget.price}).subscribe({
      next: response => {
        this.budget = response;
      },
      error: error => {
          Swal.fire('Error al recuperar la información del presupuesto', 'Inténtelo de nuevo más tarde', 'error')
        console.log(error)
        }
      })
  }

  updateStatus() {
    this.statusService.updateStatus(this.budget.id, this.selectedStatus.literal).subscribe({
      next: response => {
        this.budget = response;
      },
      error: error => {
          Swal.fire('Error al recuperar la información del presupuesto', 'Inténtelo de nuevo más tarde', 'error')
        console.log(error)
        }
      })
  }

  private clearSelectedPhoto() {
    (<HTMLInputElement>document.getElementById('image')).value = "";
  }

  private formatPrice(price:any) {
    let euro = new Intl.NumberFormat('es-ES', {
      style: 'currency',
      currency: 'EUR',
  });
    return euro.format(price)
  }

}
