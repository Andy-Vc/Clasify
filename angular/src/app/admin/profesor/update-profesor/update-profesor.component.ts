import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Usuario } from '../../../shared/model/usuario.model';
import { ProfesorService } from '../../service/profesor.service';
import { AlertService } from '../../../util/alert.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-profesor',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './update-profesor.component.html',
})
export class UpdateProfesorComponent implements OnInit {
  profesorForm!: FormGroup;
  profesorID!: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private pf: ProfesorService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.profesorForm = this.fb.group({
      idUsuario: [{ value: '', disabled: true }],
      nombreUsuario: ['', Validators.required],
      apellidoUsuario: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contrasenia: ['', Validators.required],
    });

    this.profesorID = Number(this.route.snapshot.paramMap.get('id'));

    this.pf.getByIdProfesor(this.profesorID).subscribe({
      next: (data: Usuario) => {
        this.profesorForm.patchValue(data);
      },
      error: (err) => {
        AlertService.error(err.mensaje);
        this.router.navigate(['../../'], { relativeTo: this.route });
      },
    });
  }

  guardar(): void {
    if (this.profesorForm.valid) {
      const profesorActualizado: Usuario = {
        ...this.profesorForm.getRawValue(),
        rol: 'P',
      };

      this.pf.updateProfesor(profesorActualizado).subscribe({
        next: (res) => {
          if (res.valor) {
            AlertService.success(res.mensaje);
                console.log('actualizaste y vas a:', this.route.snapshot.url, '-> ../');
            this.router.navigate(['../../'], { relativeTo: this.route });
          } else {
            AlertService.error(res.mensaje);
          }
        },
        error: (err) => {
          AlertService.error(err.mensaje);
        },
      });
    } else {
      AlertService.error('Por favor completa todos los campos obligatorios');
    }
  }

  list() {
    console.log('Vas a volvera a:', this.route.snapshot.url, '-> ../');
    this.router.navigate(['../../'], { relativeTo: this.route });
  }
}
