import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {


  ngOnInit(): void {

    const navLink = document.querySelectorAll('.nav-link');
    navLink.forEach(link => {
      if (window.location.href.endsWith(link.getAttribute('href') || '')) {
        link.classList.add('active');
      }
      link.addEventListener('click', () => {
        navLink.forEach(link => {
          link.classList.remove('active');
        });
        link.classList.add('active');
      })
    });
  }

  logout() {

  }
}
