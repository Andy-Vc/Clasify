import { Component } from '@angular/core';
import {
  RouterOutlet,
  RouterLink,
  RouterLinkWithHref,
  RouterLinkActive,
} from '@angular/router';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../servicio/auth.service';
@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    RouterLinkWithHref,
    RouterLinkActive,
  ],
  styleUrls:['admin-layout.component.css']
})
export class AdminLayoutComponent {
  darkMode = false;
  constructor(private router: Router, private authService: AuthService) {}
  toggleDarkMode() {
    this.darkMode = !this.darkMode;
    console.log('Toggle clicked. darkMode:', this.darkMode);

    if (this.darkMode) {
      document.body.classList.add('dark-mode');
    } else {
      document.body.classList.remove('dark-mode');
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
