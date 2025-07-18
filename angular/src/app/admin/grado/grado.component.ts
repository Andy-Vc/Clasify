import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Grado } from '../../shared/model/grado.model';
import { GradoService } from '../service/grado.service';
import { Router } from '@angular/router';
import { AlertService } from '../../util/alert.service';

@Component({
  selector: 'app-grado',
  imports: [CommonModule],
  templateUrl: './grado.component.html',
  styleUrl: './grado.component.css',
})
export class GradoComponent implements OnInit {
  loading = false;
  grades: Grado[] = [];

  constructor(private gradoService: GradoService, private route: Router) {}

  ngOnInit(): void {
    this.onList();
  }

  onList() {
    this.gradoService.listGrado().subscribe({
      next: (data)=> {
        this.grades = data;
      },
      error: (err) =>{
        console.log(err);
      },
    })
  }

  toggleEstado(grado: Grado){
    this.gradoService.deleteGrado(grado.idGrado!).subscribe({
      next:(res)=>{
          AlertService.success(res.mensaje);
          grado.flgEliminado =!grado.flgEliminado;
      },error:(err)=>{
        AlertService.error(err.mensaje || 'Error al cambiar el estado');
        console.log(err);
      }
    })
  }

  crearGrado(){
    this.route.navigate(['/admin/grado/crear']);
  }

  editarGrado(grado: Grado){
    this.route.navigate(['/admin/grado/editar', grado.idGrado]);
  }
}
