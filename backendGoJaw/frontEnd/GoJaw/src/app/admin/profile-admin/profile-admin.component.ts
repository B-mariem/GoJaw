import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile-admin',
  templateUrl: './profile-admin.component.html',
  styleUrls: ['./profile-admin.component.css']
})
export class ProfileAdminComponent implements OnInit {

  currentAdmin: Object = {};

  constructor(
   public apiService:ApiService,
    private actRoute: ActivatedRoute) {
    let id = this.actRoute.snapshot.paramMap.get('id');
    this.apiService.getAdminProfile(id).subscribe(res => {
      this.currentAdmin = res.msg;
    })
  }
  logout(){
    this.apiService.doLogout();
  }

  ngOnInit() {
  }

}
