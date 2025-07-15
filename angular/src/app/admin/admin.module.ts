import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfesorComponent } from './profesor/profesor.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    AdminRoutingModule,
    DashboardComponent,
    ProfesorComponent
  ]
})
export class AdminModule {}

