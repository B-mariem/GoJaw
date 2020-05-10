import { Component, OnInit, Inject } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogDesComponent } from 'src/app/des/dialog-des/dialog-des.component';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';

@Component({
  selector: 'app-dialog-delete-gouv',
  templateUrl: './dialog-delete-gouv.component.html',
  styleUrls: ['./dialog-delete-gouv.component.css']
})
export class DialogDeleteGouvComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogDeleteGouvComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private gouvService:GouvServiceService) { }

  ngOnInit() {
  }
 delete(){
    this.gouvService.deleteGouv(this.Mydata.gouv).subscribe((res)=>{
      alert('was deleted successufuly')
      
  })
   
}
}
