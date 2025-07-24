import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { CommonModule } from '@angular/common';
import { EstudianteDTO } from '../../shared/model/estudianteDTO.model';

@Component({
  selector: 'app-estudiante',
  imports: [CommonModule],
  templateUrl: './estudiante.component.html',
  styleUrls:['estudiante.component.css']
})
export class EstudianteComponent implements OnInit {
  estudiantes: EstudianteDTO[] = [];
  loading = false;

  mostrarModal = false;
  estudianteSeleccionado: EstudianteDTO | null = null;

  constructor(private as: AdminService) {}

  ngOnInit(): void {
    this.onStudents();
  }

  onStudents() {
    this.loading = true;
    this.as.getStudiantes().subscribe({
      next: (data) => {
        this.estudiantes = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        console.error('Error al cargar estudiantes', err);
      },
    });
  }

  abrirModalCursos(estudiante: EstudianteDTO) {
    this.estudianteSeleccionado = estudiante;
    this.mostrarModal = true;
  }

  cerrarModalCursos() {
    this.mostrarModal = false;
    this.estudianteSeleccionado = null;
  }
  get cursosDelEstudiante() {
    return this.estudianteSeleccionado?.cursosInscritos ?? [];
  }
}
