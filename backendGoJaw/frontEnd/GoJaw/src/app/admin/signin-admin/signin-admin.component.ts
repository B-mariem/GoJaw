import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin-admin',
  templateUrl: './signin-admin.component.html',
  styleUrls: ['./signin-admin.component.css']
})
export class SigninAdminComponent implements OnInit {

  signinForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    public apiService:ApiService,
    public router: Router
  ) {
    this.signinForm = this.fb.group({
     name: [''],
      password: ['']
    })
  }

  ngOnInit() { }

  loginUser() {
    this.apiService.signIn(this.signinForm.value)
  }

}
