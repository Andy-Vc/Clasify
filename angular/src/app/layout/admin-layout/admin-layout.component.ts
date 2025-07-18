import { Component } from '@angular/core';
import {
  RouterOutlet,
  RouterLink,
  RouterLinkWithHref,
  RouterLinkActive,
} from '@angular/router';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../auth/service/auth.service';
import { ThemeService } from '../../shared/services/theme.service';
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
  constructor(private router: Router, private authService: AuthService,public themeService: ThemeService) {}
  toggleDarkMode(): void {
  this.themeService.toggleDarkMode();
}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
