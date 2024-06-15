import { Component } from '@angular/core';
import { Budget } from '../../../models/budget';
import { FormsModule } from '@angular/forms';
import { BudgetService } from '../../../services/budget.service';
import { LoginService } from '../../../services/login.service';
import { NewBudget } from '../../../models/newBudget';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-budget',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './new-budget.component.html',
  styleUrl: './new-budget.component.css'
})
export class NewBudgetComponent{

  budget : NewBudget;
  selectedFile: File | null = null;
  imageUrl: any;
  images: Array<string>;

  constructor(private service: BudgetService,
    private loginService: LoginService,
    private router: Router
  ) {
    this.budget =new NewBudget();
    this.images = [];
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

  onSubmit(): void {
    if (!this.budget.title && !this.budget.brand && !this.budget.model && !this.budget.description) {
      if (this.selectedFile) {
        this.budget.images = ["" +this.imageUrl]
      }

      this.budget.user = this.loginService.getUsername()
      this.service.newBudget(this.budget).subscribe({
        next: response => {
          console.log(response)
          this.router.navigate(['/budgets']);
        },
        error: error => {
          if (error.status == 500) {
            Swal.fire('Error al guardar el presupuesto', 'Inténtelo de nuevo más tarde', 'error')
          } else {
            throw error;
          }
          console.log(error)
        }
      });
    }
    else {
      Swal.fire(
        'Error en la creación del presupuesto',
        'Debes rellenar todos los campos para crear un presupuesto',
        'error'
      );
    }
  }
}
