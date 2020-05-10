import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';
import { Router } from '@angular/router';
import { CategorieServiceService } from 'src/app/service/categorie-service/categorie-service.service';

@Component({
  selector: 'app-create-des',
  templateUrl: './create-des.component.html',
  styleUrls: ['./create-des.component.css']
})
export class CreateDesComponent implements OnInit {

  destinationForm: FormGroup;
 tabGouv:any
 tabCategorie:any
  constructor( public fb: FormBuilder,private categorieService: CategorieServiceService,
    private gouvService:GouvServiceService,private desService:DestinationServiceService,
    private router:Router ) { 
    this.gouvService.getGouvs().subscribe((data)=>{
      this.tabGouv=data})

    this.categorieService.getCategories().subscribe((data)=>{
      this.tabCategorie=data})

    this.mainForm();
  }

  updateGouv(e){
    this.destinationForm.get('gouv').setValue(e, {
      onlySelf: true
    })
  }
  updateCategorie(e){
    this.destinationForm.get('categorie').setValue(e, {
      onlySelf: true
    })
  }
  mainForm() {
    this.destinationForm = this.fb.group({
      libelle: ['', [Validators.required]], 
      image: ['', [Validators.required]], 
      categorie: ['', [Validators.required]], 
      gouv: ['', [Validators.required]],
     latitude: ['', [Validators.required]],
     longitude: ['', [Validators.required]],
    })
  }

  onSubmit() {

    this.desService.createDestination(this.destinationForm.value).subscribe( (res) => {
       if(res){
        alert('destination successfully created!')
        this.router.navigate(["edit-des"])
        this.destinationForm.reset()

       }
     
      
      }, (error) => {
        alert(error);
      });
  
    
  }
  ngOnInit() {
  }
}
