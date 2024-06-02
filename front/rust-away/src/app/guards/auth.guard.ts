import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { LoginService } from '../services/login.service';

export const authGuard: CanActivateFn = (route, state) => {
  console.log("Entra")
  const service = inject(LoginService);
  const router = inject(Router);
  if (service.isAuth()) {
    if (isTokenExpired()) {
      service.logout();
      router.navigate(['/login']);
      return false;
    }
    return true;
  }
  router.navigate(['/login']);
  return false;
};


// Method to check if the current token is no longer available
const isTokenExpired = () => {
  const service = inject(LoginService);
  const token = service.token;
  const payload = service.getPayload(token);
  const exp = payload.exp;
  console.log(exp)
  const now = new Date().getTime() / 1000;
  return (now > exp) ? true : false;
}

