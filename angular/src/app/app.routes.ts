import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'estudiante',
    loadChildren: () =>
      import('./estudiante/estudiante.module').then((m) => m.EstudianteModule),
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then((m) => m.AdminModule),
  },
  {
    path: 'profesor',
    loadChildren: () =>
      import('./profesor/profesor.module').then((m) => m.ProfesorModule),
  },

  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },

  { path: '**', redirectTo: 'auth/login' },
];
