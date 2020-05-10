import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-des-navbar',
  templateUrl: './des-navbar.component.html',
  styleUrls: ['./des-navbar.component.css']
})
export class DesNavbarComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  goToSearch(value){
    this.router.navigate(['search-destination/',value]);
  }
}
