import { Component } from '@angular/core';
import { AdminService } from '../service/curso.service';
import { CursoEstudiantesDTO } from '../../shared/model/cursoEstudiantesDTO.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-curso',
  imports: [CommonModule],
  templateUrl: './curso.component.html',
  styleUrl: './curso.component.css',
})
export class CursoComponent {
  cursos: CursoEstudiantesDTO[] = [];
  constructor(private adminService: AdminService) {}

  ngOnInit() {
    const token = localStorage.getItem('token');

    if (token) {
      this.adminService.getCursosProfesor(token).subscribe({
        next: (data) => {
          this.cursos = data;
          console.log('Cursos recibidos:', data);
        },
        error: (err) => {
          console.error(err);
        },
      });
    } else {
      console.warn('No hay token disponible');
    }
  }
}
