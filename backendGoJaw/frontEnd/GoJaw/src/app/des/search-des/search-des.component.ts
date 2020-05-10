import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { ActivatedRoute } from '@angular/router';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';
import { destination } from 'src/app/models/destinations';

@Component({
  selector: 'app-search-des',
  templateUrl: './search-des.component.html',
  styleUrls: ['./search-des.component.css']
})
export class SearchDesComponent implements OnInit {

  tabDestinations:destination[];
  gouv:any
  constructor(private desService:DestinationServiceService,private actRoute: ActivatedRoute) {
    this.gouv = this.actRoute.snapshot.paramMap.get('gouv');
   
    this.desService.getDestinationByGouv(this.gouv).subscribe((data) => {
      this.tabDestinations =data ;
      this.tabDestinations .sort((a, b)=>{
        var libelleA=a.libelle.toLowerCase(), libelleB=b.libelle.toLowerCase()
           if (libelleA < libelleB) //sort string ascending
             return -1 
             })
        })
   }

   openDialogEditDestination(value){
    this.desService.openDialogEditDestination(value)
  }

  ngOnInit() {
  }

}
