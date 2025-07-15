import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfesorComponent } from './profesor.component';
import { CreateProfesorComponent } from './create-profesor/create-profesor.component';
import { UpdateProfesorComponent } from './update-profesor/update-profesor.component';

const routes: Routes = [
  { path: '', component: ProfesorComponent },           
  { path: 'crear', component: CreateProfesorComponent,data: { title: 'Crear Profesor' } },  
  { path: 'editar/:id', component: UpdateProfesorComponent,data: { title: 'Actualizar Profesor' } } 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfesorRoutingModule {}
