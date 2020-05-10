import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ApiService } from 'src/app/service/api.service';
import { CategorieServiceService } from 'src/app/service/categorie-service/categorie-service.service';

@Component({
  selector: 'app-delete-categorie',
  templateUrl: './delete-categorie.component.html',
  styleUrls: ['./delete-categorie.component.css']
})
export class DeleteCategorieComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteCategorieComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private categorieService:CategorieServiceService) { 
     
               
    }

  ngOnInit() {
   
  }
delete(){
  this.categorieService.deleteCategorie(this.Mydata.id).subscribe((res)=>{
    this.dialogRef.close(res)
  })
}
close(){
  this.dialogRef.close()
}
}