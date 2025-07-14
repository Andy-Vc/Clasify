import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; 
import { EstudianteFooterComponent } from './estudiante-footer.component';

@Component({
  selector: 'app-estudiante-layout',
  templateUrl: './estudiante-layout.component.html',
  standalone: true,
  imports: [
    RouterOutlet,
    EstudianteFooterComponent
  ]
})
export class EstudianteLayoutComponent {}
