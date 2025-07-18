import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CursoService } from '../../service/curso.service';
import { CursoDTO } from '../../../shared/model/cursoDTO.model';
import { AlertService } from '../../../util/alert.service';
import { CommonModule } from '@angular/common';
import { ProfesorService } from '../../service/profesor.service';
import { GradoService } from '../../service/grado.service';
import { Usuario } from '../../../shared/model/usuario.model';
import { Grado } from '../../../shared/model/grado.model';

@Component({
  selector: 'app-update-curso',
  templateUrl: './update-curso.component.html',
  imports: [ReactiveFormsModule, CommonModule],
})
export class UpdateCursoComponent implements OnInit {
  cursoForm!: FormGroup;
  cursoID!: string;
  profesores: Usuario[] = [];
  grados: Grado[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private cs: CursoService,
    private fb: FormBuilder,
    private usuarioService: ProfesorService,
    private gradoService: GradoService
  ) {}

  ngOnInit(): void {
    this.listarProfesores();
    this.listarGrados();

    this.cursoForm = this.fb.group({
      idCurso: [{ value: '', disabled: true }],
      nombreCurso: ['', Validators.required],
      idProfesor: [null, Validators.required],
      idGrado: [null, Validators.required],
    });

    this.cursoID = String(this.route.snapshot.paramMap.get('id'));

    this.cs.getByIdCurso(this.cursoID).subscribe({
      next: (data: CursoDTO) => {
        const profesor =
          this.profesores.find(
            (p) => p.idUsuario === data.idProfesor?.idUsuario
          ) ?? null;
        const grado =
          this.grados.find((g) => g.idGrado === data.idGrado?.idGrado) ?? null;

        this.cursoForm.patchValue({
          idCurso: data.idCurso,
          nombreCurso: data.nombreCurso,
          idProfesor: profesor,
          idGrado: grado,
        });
      },
      error: (err) => {
        AlertService.error(err.mensaje || 'Error al cargar el curso');
        this.router.navigate(['../../'], { relativeTo: this.route });
      },
    });
  }

  update() {
    if (this.cursoForm.valid) {
      const cursoActualizado: CursoDTO = {
        ...this.cursoForm.getRawValue(),
      };

      this.cs.updateCurso(cursoActualizado).subscribe({
        next: (res) => {
          if (res.valor) {
            AlertService.success(res.mensaje);
            this.router.navigate(['../../'], { relativeTo: this.route });
          } else {
            AlertService.error(res.mensaje);
          }
        },
        error: (err) => {
          AlertService.error(err.mensaje || 'Error al actualizar el curso');
        },
      });
    } else {
      AlertService.error('Por favor, completa todos los campos obligatorios');
    }
  }

  list() {
    this.router.navigate(['../../'], { relativeTo: this.route });
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
