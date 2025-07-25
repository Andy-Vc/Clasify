import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProfesorService } from '../service/curso.service';
import { CursoEstudiantesDTO } from '../../shared/model/cursoEstudiantesDTO.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotaDTO } from '../../shared/model/notaDTO.model';
import { NotaProfesorService } from '../service/nota.service';
import { AlertService } from '../../util/alert.service';

declare var bootstrap: any;

@Component({
  selector: 'app-curso',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class CursoComponent implements OnInit {
  cursos: CursoEstudiantesDTO[] = [];
  cursoSeleccionado: CursoEstudiantesDTO | null = null;
  estudianteSeleccionado: any = null;
  nota: number | null = null;
  nroNota: number | null = null;
  comentario: string = '';

  constructor(
    private profesorService: ProfesorService,
    private notaService: NotaProfesorService
  ) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');

    if (token) {
      this.profesorService.getCursosProfesor(token).subscribe({
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

  abrirModalCurso(curso: CursoEstudiantesDTO): void {
    this.cursoSeleccionado = curso;
    const modalElement = document.getElementById('modalCurso');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }
  tieneEstudiantes(): boolean {
    return !!(
      this.cursoSeleccionado &&
      this.cursoSeleccionado.estudiantes &&
      this.cursoSeleccionado.estudiantes.length > 0
    );
  }

  seleccionarEstudiante(estudiante: any) {
    this.estudianteSeleccionado = estudiante;

    if (!this.nroNota) {
      this.nroNota = 1;
    }

    if (this.cursoSeleccionado && estudiante.idUsuario) {
      this.cargarNotaEstudiante(
        this.cursoSeleccionado.idCurso,
        estudiante.idUsuario,
        this.nroNota
      );
    } else {
      this.nota = null;
      this.comentario = '';
    }
  }

  onCambioNroNota() {
    if (
      this.cursoSeleccionado &&
      this.estudianteSeleccionado &&
      this.nroNota != null
    ) {
      this.cargarNotaEstudiante(
        this.cursoSeleccionado.idCurso,
        this.estudianteSeleccionado.idUsuario,
        this.nroNota
      );
    } else {
      this.nota = null;
      this.comentario = '';
    }
  }

  cargarNotaEstudiante(idCurso: string, idUsuario: number, nroNota: number) {
    this.notaService.getNota(idCurso, idUsuario, nroNota).subscribe(
      (nota) => {
        if (!this.estudianteSeleccionado.notas) {
          this.estudianteSeleccionado.notas = [];
        }

        const nroNotaNum = Number(nroNota);
        const index = this.estudianteSeleccionado.notas.findIndex(
          (n: NotaDTO) => n.nroNota === nroNotaNum
        );

        if (index !== -1) {
          this.estudianteSeleccionado.notas[index] = nota;
        } else {
          this.estudianteSeleccionado.notas.push(nota);
        }

        this.nota = nota.calificacion;
        this.comentario = nota.comentario;
      },
      (err) => {
        this.nota = null;
        this.comentario = '';
      }
    );
  }

  calcularPromedio(notas: NotaDTO[]): number {
    if (!notas || notas.length === 0) return 0;
    const suma = notas.reduce((acc, n) => acc + (n.calificacion ?? 0), 0);
    return suma / notas.length;
  }

  crearNota() {
    if (
      this.cursoSeleccionado &&
      this.estudianteSeleccionado &&
      this.nroNota != null &&
      this.nota != null
    ) {
      const nroNotaNum = Number(this.nroNota);
      const notaDTO: NotaDTO = {
        idCurso: this.cursoSeleccionado.idCurso,
        idUsuario: this.estudianteSeleccionado.idUsuario,
        nroNota: nroNotaNum,
        calificacion: this.nota,
        comentario: this.comentario,
        fechaRegistro: new Date(),
      };

      this.notaService.saveOrUpdateNota(notaDTO).subscribe({
        next: (res) => {
          if (res.valor) {
            if (!this.estudianteSeleccionado.notas) {
              this.estudianteSeleccionado.notas = [];
            }

            const index = this.estudianteSeleccionado.notas.findIndex(
              (n: NotaDTO) => Number(n.nroNota) === nroNotaNum
            );

            if (index !== -1) {
              // Reemplaza la nota existente
              this.estudianteSeleccionado.notas[index] = notaDTO;
            } else {
              // Agrega nueva nota
              this.estudianteSeleccionado.notas.push(notaDTO);
            }

            this.estudianteSeleccionado.promedio = this.calcularPromedio(
              this.estudianteSeleccionado.notas
            );

            AlertService.success(res.mensaje);
            this.cancelarCalificacion();
          } else {
            AlertService.error(res.mensaje);
          }
        },
        error: () => {
          AlertService.error('Error registrando o actualizando nota');
        },
      });
    }
  }

  cancelarCalificacion() {
    this.estudianteSeleccionado = null;
    this.nota = null;
    this.nroNota = null;
    this.comentario = '';
  }
}
