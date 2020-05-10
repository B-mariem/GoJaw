import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { MatDialog } from '@angular/material';
import { DialogDesComponent } from '../dialog-des/dialog-des.component';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';
import { destination } from 'src/app/models/destinations';

@Component({
  selector: 'app-edit-des',
  templateUrl: './edit-des.component.html',
  styleUrls: ['./edit-des.component.css']
})
export class EditDesComponent implements OnInit {
TabDestinations:any;

  constructor(private desService:DestinationServiceService,public dialog: MatDialog) {
    this.desService.getDestinations().subscribe((data)=>{
  
      this.TabDestinations=data
     this.TabDestinations.sort((a, b)=>{
        var gouvA=a.gouv.toLowerCase(), gouvB=b.gouv.toLowerCase()
               if (gouvA < gouvB) //sort string ascending
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
