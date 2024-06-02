import { Routes } from '@angular/router';
import { RustAwayComponent } from './components/rust-away.component';
import { AuthComponent } from './components/auth/auth.component';
import { SignupComponent } from './components/user/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { BudgetComponent } from './components/budgets/budget/budget.component';
import { NewBudgetComponent } from './components/budgets/new-budget/new-budget.component';
import { EditUserComponent } from './components/user/edit-user/edit-user.component';
import { UserInfoComponent } from './components/user/user-info/user-info.component';
import { authGuard } from './guards/auth.guard';
import { BudgetsListComponent } from './components/budgets/budgets-list/budgets-list.component';
import { InfoComponent } from './components/info/info.component';

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
        path: 'user-info',
        component: UserInfoComponent,
        canActivate: [authGuard]
    },
    {
        path: 'edit-user',
        component: EditUserComponent,
        canActivate: [authGuard]
    },
    {
        path: 'budgets',
        component: BudgetsListComponent,
        canActivate: [authGuard]
    },
    {
        path: 'new-budget',
        component: NewBudgetComponent,
        canActivate: [authGuard]
    },
    {
        path: 'budget-info',
        component: BudgetComponent,
        canActivate: [authGuard]
    },
    {
        path: 'info',
        component: InfoComponent,
    }
];
