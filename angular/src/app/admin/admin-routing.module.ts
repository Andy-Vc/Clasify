import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { EstudianteComponent } from './estudiante/estudiante.component';

const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
        data: { title: 'Dashboard' },
      },
      {
        path: 'estudiante',
        component: EstudianteComponent,
        data: { title: 'Estudiantes' },
      },
      {
        path: 'profesor',
        loadChildren: () =>
          import('./profesor/profesor.module').then((m) => m.ProfesorModule),
        data: { title: 'Profesores' },
      },
      {
        path: 'grado',
        loadChildren: () =>
          import('./grado/grado.module').then((m) => m.GradoModule),
        data: { title: 'Grados' },
      },
      {
        path: 'curso',
        loadChildren: () =>
          import('./curso/curso.module').then((m) => m.CursoModule),
        data: { title: 'Cursos' },
      },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
