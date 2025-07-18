import { Component, OnInit } from '@angular/core';
import { CursoDTO } from '../../shared/model/cursoDTO.model';
import { CursoService } from '../service/curso.service';
import { AlertService } from '../../util/alert.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-curso',
  imports: [CommonModule],
  templateUrl: './curso.component.html',
  styleUrl: './curso.component.css'
})
export class CursoComponent implements OnInit {
  loading = false;
  curses: CursoDTO[] = [];

  constructor(private cursoService: CursoService, private route: Router) {}

  ngOnInit(): void {
    this.onList();
  }

  onList(){
    this.cursoService.listCurso().subscribe({
      next: (data)=> {
        this.curses = data;
      },
      error: (err) =>{
        console.log(err);
      },
    })
  }

  toggleEstado(curso: CursoDTO){
      this.cursoService.deleteCurso(curso.idCurso!).subscribe({
        next:(res)=>{
            AlertService.success(res.mensaje);
            curso.flgEliminado =!curso.flgEliminado;
        },error:(err)=>{
          AlertService.error(err.mensaje || 'Error al cambiar el estado');
          console.log(err);
        }
      })
    }
  
    crearCurso(){
      this.route.navigate(['/admin/curso/crear']);
    }
  
    editarCurso(curso: CursoDTO){
      this.route.navigate(['/admin/curso/editar', curso.idCurso]);
    }

    detalleCurso(curso: CursoDTO){
      this.route.navigate(['/admin/curso/detalle',curso.idCurso]);
    }
}
