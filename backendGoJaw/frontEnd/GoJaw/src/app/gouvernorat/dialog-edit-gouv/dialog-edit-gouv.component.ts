import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogDesComponent } from 'src/app/des/dialog-des/dialog-des.component';
import { ApiService } from 'src/app/service/api.service';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';

@Component({
  selector: 'app-dialog-edit-gouv',
  templateUrl: './dialog-edit-gouv.component.html',
  styleUrls: ['./dialog-edit-gouv.component.css']
})
export class DialogEditGouvComponent implements OnInit {
  form: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<DialogEditGouvComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private gouvService:GouvServiceService) { 
      this.getGouv(this.Mydata.id_gouv);  
    }

  ngOnInit() {
 

    this.form = this.fb.group({
     gouv: ['', []],
     image:['',[]]
   
  });
  }
  getGouv(id) {
    this.gouvService.getGouv(id).subscribe(data => {
      this.form.setValue({
        gouv: data['gouv'],
        image: data['image'],
       
      });
    });
  }
edit(){
   this.gouvService.updateGouv(this.Mydata.id_gouv,this.form.value).subscribe((res)=>{
    this.dialogRef.close(res)
  })
  
}
close(){
  this.dialogRef.close()
}
}
