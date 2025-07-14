import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EstudianteLayoutComponent } from '../layout/estudiante-layout/estudiante-layout.component';

const routes: Routes = [
  {
    path: '',
    component: EstudianteLayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes), EstudianteLayoutComponent,
    DashboardComponent ],
  exports: [RouterModule]
})
export class EstudianteRoutingModule {}
