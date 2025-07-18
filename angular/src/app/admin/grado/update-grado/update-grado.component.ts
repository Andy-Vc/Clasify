import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GradoService } from '../../service/grado.service';
import { Grado } from '../../../shared/model/grado.model';
import { AlertService } from '../../../util/alert.service';

@Component({
  selector: 'app-update-grado',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './update-grado.component.html',
})
export class UpdateGradoComponent {
  gradoForm!: FormGroup;
  gradoID!: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private gs: GradoService,
    private fb: FormBuilder
  ) {}
  ngOnInit(): void {
    this.gradoForm = this.fb.group({
      idGrado: [{ value: '', disabled: true }],
      nombreGrado: ['', Validators.required],
    });

    this.gradoID = Number(this.route.snapshot.paramMap.get('id'));

    this.gs.getByIdGrado(this.gradoID).subscribe({
      next: (data: Grado) => {
        this.gradoForm.patchValue(data);
      },
      error: (err) => {
        AlertService.error(err.mensaje);
        this.router.navigate(['../../'], { relativeTo: this.route });
      },
    });
  }
  guardar(): void {
    if (this.gradoForm.valid) {
      const gradoActualizado: Grado = {
        ...this.gradoForm.getRawValue(),
        rol: 'P',
      };

      this.gs.updateGrado(gradoActualizado).subscribe({
        next: (res) => {
          if (res.valor) {
            AlertService.success(res.mensaje);
            console.log(
              'actualizaste y vas a:',
              this.route.snapshot.url,
              '-> ../'
            );
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
    this.router.navigate(['../../'], { relativeTo: this.route });
  }
}
