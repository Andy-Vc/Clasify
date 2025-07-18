import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfesorService } from '../service/profesor.service';
import { Router } from '@angular/router';
import { Usuario } from '../../shared/model/usuario.model';
import { AlertService } from '../../util/alert.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './profesor.component.html',
  styleUrls:['profesor.component.css'],
  imports: [CommonModule],
  standalone: true,
})
export class ProfesorComponent implements OnInit {
  loading = false;
  teachers: Usuario[] = [];

  constructor(
    private profesorService: ProfesorService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.onList();
  }

  onList() {
    this.profesorService.listProfesor().subscribe({
      next: (data) => {
        this.teachers = data;
        console.log('Profesores:', this.teachers);
      },
      error: (err) => {
        console.error('Error al cargar profesores', err);
      },
    });
  }

  toggleEstado(teacher: Usuario) {
    this.profesorService.deleteProfesor(teacher.idUsuario!).subscribe({
      next: (res) => {
        AlertService.success(res.mensaje);
        // Cambiar estado local solo si se actualizó exitosamente
        teacher.flgEliminado = !teacher.flgEliminado;
      },
      error: (err) => {
        AlertService.error(err.mensaje || 'Error al cambiar estado');
        console.error('Error al cambiar estado', err);
      },
    });
  }

  crearProfesor() {
    this.route.navigate(['/admin/profesor/crear']);
  }

  editarProfesor(teacher: Usuario) {
    this.route.navigate(['/admin/profesor/editar', teacher.idUsuario]);
  }
}
