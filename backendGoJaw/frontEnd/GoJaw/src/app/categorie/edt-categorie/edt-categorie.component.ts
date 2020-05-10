import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ApiService } from 'src/app/service/api.service';
import { CategorieServiceService } from 'src/app/service/categorie-service/categorie-service.service';

@Component({
  selector: 'app-edt-categorie',
  templateUrl: './edt-categorie.component.html',
  styleUrls: ['./edt-categorie.component.css']
})
export class EdtCategorieComponent implements OnInit {
  form: FormGroup;
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<EdtCategorieComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private categorieService:CategorieServiceService) { 
      this.categorieService.getcategorie(Mydata.id).subscribe((data)=>{
        this.form.setValue({
          libelle: data['libelle'],
             })
      })
               
    }

  ngOnInit() {
    this.form = this.fb.group({
      libelle: ['', []]
  
  });
  }
edit(){
  this.categorieService.updateCategorie(this.Mydata.id,this.form.value).subscribe((res)=>{
    this.dialogRef.close(res)
  })
}
}
