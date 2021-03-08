import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(
    private edgeService: EdgeService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.edgeService.logout();
    this.router.navigate(['/login']);
  }
}
