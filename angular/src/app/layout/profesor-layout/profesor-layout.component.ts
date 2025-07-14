import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; 
import { ProfesorFooterComponent } from './profesor-footer.component';

@Component({
  selector: 'app-profesor-layout',
  templateUrl: './profesor-layout.component.html',
  standalone: true,
  imports: [
    RouterOutlet,
    ProfesorFooterComponent
  ]
})
export class ProfesorLayoutComponent {}
