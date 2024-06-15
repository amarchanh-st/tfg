import { Component } from '@angular/core';
import { User } from '../../../models/user';
import { LoginService } from '../../../services/login.service';
import { BudgetService } from '../../../services/budget.service';
import { Budget } from '../../../models/budget';
import Swal from 'sweetalert2';
import { ActivatedRoute, Route, RouterModule } from '@angular/router';

@Component({
  selector: 'app-budgets-list',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './budgets-list.component.html',
  styleUrl: './budgets-list.component.css'
})
export class BudgetsListComponent {

  worker:boolean;
  users : Array<User>;
  budgets: Budget[];


  constructor(private loginService:LoginService,
    private service: BudgetService,
  private route: ActivatedRoute
  ) {
    this.worker = this.loginService.isAdmin();
    this.users = [new User()];
    this.budgets = [];
  }



  ngOnInit(): void {
    console.log("Entra")
    this.service.findAll().subscribe({
      next: response => {
        console.log(response)
        this.budgets = response;

      },
      error: error => {
        if (error.status == 500) {
          Swal.fire('Error al recuperar la información de los presupuestos', 'Inténtelo de nuevo más tarde', 'error')
        } else {
          throw error;
        }
        console.log(error)
      }
    })
  }
}
