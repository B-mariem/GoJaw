import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ApiService } from 'src/app/service/api.service';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-dialog-des',
  templateUrl: './dialog-des.component.html',
  styleUrls: ['./dialog-des.component.css']
})
export class DialogDesComponent implements OnInit {
  form: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<DialogDesComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private apiService:ApiService) { 
               
    }

  ngOnInit() {
    this.form = this.fb.group({
     latitude: ['', []],
     longitude:['',[]]
   
  });
  }
save(){
  
  this.apiService.addPosition(this.Mydata.id_destination,this.form.value).subscribe((res)=>{
    this.dialogRef.close(res)
  })
  
}
}
