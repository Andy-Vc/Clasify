import { NgModule } from "@angular/core";
import { RouterModule,Routes } from "@angular/router";
import { GradoComponent } from "./grado.component";
import { CreateGradoComponent } from "./create-grado/create-grado.component";
import { UpdateGradoComponent } from "./update-grado/update-grado.component";

const routes: Routes = [
    {path:'', component:GradoComponent},
    {path:'crear', component:CreateGradoComponent,data: { title: 'Crear Grado' } },
    {path:'editar/:id', component:UpdateGradoComponent,data: { title: 'Actualizar Grado' } },
]

@NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
})

export class GradoRoutingModule{}