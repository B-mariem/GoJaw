import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { MatDialog } from '@angular/material';
import { DialogDesComponent } from 'src/app/des/dialog-des/dialog-des.component';
import { DialogEditGouvComponent } from '../dialog-edit-gouv/dialog-edit-gouv.component';
import { DialogDeleteGouvComponent } from '../dialog-delete-gouv/dialog-delete-gouv.component';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';
import { gouv } from 'src/app/models/gouv';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';

@Component({
  selector: 'app-list-gouv',
  templateUrl: './list-gouv.component.html',
  styleUrls: ['./list-gouv.component.css']
})
export class ListGouvComponent implements OnInit {
tabGouv:any;
tabEvent:any;
  constructor(private gouvService: GouvServiceService,public dialog: MatDialog, private desService:DestinationServiceService) { 
     this.gouvService.getGouvs().subscribe((data)=>{
       this.tabGouv=data
       this.tabGouv.sort((a, b)=>{
        var gouvA=a.gouv.toLowerCase(), gouvB=b.gouv.toLowerCase()
               if (gouvA < gouvB) //sort string ascending
                  return -1 
             })
   
  }) }

  ngOnInit() {
  }
  openDialogEditGouv(id): void {
    const dialogRef = this.dialog.open(DialogEditGouvComponent, {
      data:{
        id_gouv:id
      }  
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
       location.reload();
      console.log(result);
      
    
    });
  }


  openDialogdeleteGouv(gouv): void {
    const dialogRef = this.dialog.open(DialogDeleteGouvComponent, {
      data:{
        gouv:gouv
      }  
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
       location.reload();
      console.log(result);
      
    
    });
  }
  Delete(value){
    this.desService.getDestinationByGouv(value).subscribe((data)=>{
      this.tabEvent=data;
      
      if(this.tabEvent.length!=0){
        this.openDialogdeleteGouv(value)
      }else{
        this.gouvService.deleteGouv(value).subscribe(()=>{
          location.reload(); 
        })
      }
       
     })
  
  }
}
