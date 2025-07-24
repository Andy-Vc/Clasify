import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; 

@Component({
  selector: 'app-estudiante-layout',
  templateUrl: './estudiante-layout.component.html',
  standalone: true,
  imports: [
    RouterOutlet
  ]
})
export class EstudianteLayoutComponent {}
