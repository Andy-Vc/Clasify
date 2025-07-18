import { Component, OnInit } from '@angular/core';
import { CursoDTO } from '../../../shared/model/cursoDTO.model';
import { Grado } from '../../../shared/model/grado.model';
import { ProfesorService } from '../../service/profesor.service';
import { GradoService } from '../../service/grado.service';
import { CursoService } from '../../service/curso.service';
import { Usuario } from '../../../shared/model/usuario.model';
import { AlertService } from '../../../util/alert.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-curso',
  imports: [CommonModule,FormsModule,     
    ReactiveFormsModule  ],
  templateUrl: './create-curso.component.html',
})
export class CreateCursoComponent implements OnInit {
  cursoForm!: FormGroup;
  curso: CursoDTO = {};
  profesores: Usuario[] = [];
  grados: Grado[] = [];

  constructor(
    private usuarioService: ProfesorService,
    private gradoService: GradoService,
    private cursoService: CursoService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.cursoForm = this.fb.group({
      nombreCurso: ['', Validators.required],
      idProfesor: [null, Validators.required],
  idGrado: [null, Validators.required],
    });
    this.listarProfesores();
    this.listarGrados();
  }

  guardar() {
    if (this.cursoForm.valid) {
      const crearCurso: CursoDTO = this.cursoForm.value;

      this.cursoService.createCurso(crearCurso).subscribe({
        next: (res) => {
          if (res.valor) {
            AlertService.success(res.mensaje);
            this.router.navigate(['../'], { relativeTo: this.route });
          } else {
            AlertService.error(res.mensaje);
          }
        },
        error: (err) => {
          console.error('Server error', err);
          AlertService.error(err.mensaje || 'Error al crear el curso');
        },
      });
    } else {
      AlertService.error('Por favor, completa todos los campos obligatorios');
    }
  }
  list() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  listarProfesores(): void {
    this.usuarioService.listProfesor().subscribe({
      next: (data) => (this.profesores = data),
      error: (err) => console.error('Error al cargar profesores', err),
    });
  }

  listarGrados(): void {
    this.gradoService.listGrado().subscribe({
      next: (data) => (this.grados = data),
      error: (err) => console.error('Error al cargar grados', err),
    });
  }
}
