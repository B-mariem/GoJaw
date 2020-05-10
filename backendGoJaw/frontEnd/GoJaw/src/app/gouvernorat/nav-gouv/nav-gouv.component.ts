import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-gouv',
  templateUrl: './nav-gouv.component.html',
  styleUrls: ['./nav-gouv.component.css']
})
export class NavGouvComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  goToSearch(value){
    this.router.navigate(['search-Gouv/',value]);
  }
}
