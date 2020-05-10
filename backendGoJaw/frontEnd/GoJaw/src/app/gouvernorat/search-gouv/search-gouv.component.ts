import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { ActivatedRoute } from '@angular/router';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';

@Component({
  selector: 'app-search-gouv',
  templateUrl: './search-gouv.component.html',
  styleUrls: ['./search-gouv.component.css']
})
export class SearchGouvComponent implements OnInit {
searchGouv:any;
  constructor(private gouvService:GouvServiceService,private actRoute: ActivatedRoute) {
    let gouv = this.actRoute.snapshot.paramMap.get('gouv');
    this.gouvService.SearchGouv(gouv).subscribe((data) => {
      this.searchGouv =data ;
  })

   }

  ngOnInit() {
  }
  
}
