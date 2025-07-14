import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfesorLayoutComponent } from '../layout/profesor-layout/profesor-layout.component';

const routes: Routes = [
  {
    path: '',
    component: ProfesorLayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes), ProfesorLayoutComponent, 
    DashboardComponent ],
  exports: [RouterModule]
})
export class ProfesorRoutingModule {}
