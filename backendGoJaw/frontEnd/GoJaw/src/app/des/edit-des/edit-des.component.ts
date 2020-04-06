import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { MatDialog } from '@angular/material';
import { DialogDesComponent } from '../dialog-des/dialog-des.component';

@Component({
  selector: 'app-edit-des',
  templateUrl: './edit-des.component.html',
  styleUrls: ['./edit-des.component.css']
})
export class EditDesComponent implements OnInit {
destination;
  constructor(private apiService:ApiService,public dialog: MatDialog) {
    this.apiService.getDestinations().subscribe((data)=>{
      this.destination=data;
    })
   }
   openDialogAddPosition(value): void {
    const dialogRef = this.dialog.open(DialogDesComponent, {
      data:{
        id_destination:value
      }  
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      location.reload();
      console.log(result);
      
    
    });
  }
  ngOnInit() {
  }

}
