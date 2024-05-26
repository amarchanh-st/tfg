import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  // When a user try to access to a restringed area, will be redirected to the login page
  inject(Router).navigate(['/login'])
  return false;
};
