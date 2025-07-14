import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule
  ]
})
export class AdminLayoutComponent {
  darkMode = false;

  toggleDarkMode() {
  this.darkMode = !this.darkMode;
  console.log('Toggle clicked. darkMode:', this.darkMode);

  if (this.darkMode) {
    document.body.classList.add('dark-mode');
  } else {
    document.body.classList.remove('dark-mode');
  }
}

}
