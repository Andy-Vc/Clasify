import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { RegisterComponent } from './componentes/register/register.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent,data: { title: 'Iniciar sesión' } },
  { path: 'register', component: RegisterComponent,data: { title: 'Registrate' } },
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

  { path: '', redirectTo: 'login', pathMatch: 'full' },

  { path: '**', redirectTo: 'login' },
];
