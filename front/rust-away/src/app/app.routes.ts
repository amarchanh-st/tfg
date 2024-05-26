import { Routes } from '@angular/router';
import { RustAwayComponent } from './components/rust-away.component';
import { AuthComponent } from './components/auth/auth.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { BudgetComponent } from './components/budgets/budget/budget.component';
import { NewBudgetComponent } from './components/budgets/new-budget/new-budget.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'home'
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'login',
        component: AuthComponent
    },
    {
        path: 'signup',
        component: SignupComponent
    },
    {
        path: 'budgets',
        component: BudgetComponent
    },
    {
        path: 'new-budget',
        component: NewBudgetComponent
    }
];
