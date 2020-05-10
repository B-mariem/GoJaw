import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-event',
  templateUrl: './navbar-event.component.html',
  styleUrls: ['./navbar-event.component.css']
})
export class NavbarEventComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  goToSearch(value){
    this.router.navigate(['search-event/',value]);
  }
}
