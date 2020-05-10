import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ApiService } from 'src/app/service/api.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';
import { CategorieServiceService } from 'src/app/service/categorie-service/categorie-service.service';

@Component({
  selector: 'app-dialog-des',
  templateUrl: './dialog-des.component.html',
  styleUrls: ['./dialog-des.component.css']
})
export class DialogDesComponent implements OnInit {
  form: FormGroup;
  tabCategorie:any
  tabGouv:any
  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<DialogDesComponent>,
    @Inject(MAT_DIALOG_DATA) public Mydata:any,private desService:DestinationServiceService,
    private categorieService:CategorieServiceService,
    private gouvService:GouvServiceService) { 
      this.categorieService.getCategories().subscribe((data)=>{
        this.tabCategorie=data})
        this.gouvService.getGouvs().subscribe((data)=>{
          this.tabGouv=data})
      this.desService.getDestinationById(Mydata.id_destination).subscribe((data)=>{
        this.form.setValue({
          libelle: data['libelle'],
          image: data['image'],
          categorie: data['categorie'],
          gouv: data['gouv'],
          latitude:data['latitude'],
          longitude:data['longitude']
          


        })
      })
               
    }

  ngOnInit() {
    this.form = this.fb.group({
      libelle: ['', []],
      image: ['', []],
      categorie: ['', []],
      gouv: ['', []],
      latitude: ['', []],
      longitude:['',[]]
   
  });
  }
  updateCategorie(e){
    this.form.get('categorie').setValue(e, {
      onlySelf: true
    })
  }
  updateGouv(e){
    this.form.get('gouv').setValue(e, {
      onlySelf: true
    })
  }
edit(){
  this.desService.updateDestination(this.Mydata.id_destination,this.form.value).subscribe((res)=>{
    this.dialogRef.close(res)
  })
}
close(){
  this.dialogRef.close();
}
}
