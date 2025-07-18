import { RouterModule, Routes } from "@angular/router";
import { CursoComponent } from "./curso.component";
import { CreateCursoComponent } from "./create-curso/create-curso.component";
import { UpdateCursoComponent } from "./update-curso/update-curso.component";
import { NgModule } from "@angular/core";
import { DetailsCursoComponent } from "./details-curso/details-curso.component";

const routes: Routes =[
    {path: '', component: CursoComponent},
    {path: 'crear', component: CreateCursoComponent,data:{title: 'Crear Curso'}},
    {path: 'editar/:id', component: UpdateCursoComponent, data:{title: 'Actualizar Curso'}},
    {path: 'detalle/:id', component: DetailsCursoComponent, data:{title:'Detalle Curso'}}
]

@NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
})

export class CursoRoutingModule{}